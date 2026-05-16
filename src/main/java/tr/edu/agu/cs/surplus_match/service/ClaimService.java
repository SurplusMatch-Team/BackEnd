package tr.edu.agu.cs.surplus_match.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import tr.edu.agu.cs.surplus_match.dto.CreateClaimRequest;
import tr.edu.agu.cs.surplus_match.dto.PatchClaimRequest;
import tr.edu.agu.cs.surplus_match.dto.WithdrawClaimRequest;
import tr.edu.agu.cs.surplus_match.model.Claim;
import tr.edu.agu.cs.surplus_match.model.ClaimStatus;
import tr.edu.agu.cs.surplus_match.model.Product;
import tr.edu.agu.cs.surplus_match.model.ProductStatus;
import tr.edu.agu.cs.surplus_match.model.Role;
import tr.edu.agu.cs.surplus_match.model.User;
import tr.edu.agu.cs.surplus_match.repository.ClaimRepository;
import tr.edu.agu.cs.surplus_match.repository.ProductRepository;
import tr.edu.agu.cs.surplus_match.repository.UserRepository;

import java.util.List;

@Service
public class ClaimService {

    private final ClaimRepository claimRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    public ClaimService(ClaimRepository claimRepository,
                        ProductRepository productRepository,
                        UserRepository userRepository) {
        this.claimRepository = claimRepository;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public Claim createClaim(CreateClaimRequest request) {
        User claimant = userRepository.findById(request.getClaimantId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Claimant not found."));

        if (claimant.getRole() != Role.NGO) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Only NGO users can create claims.");
        }

        Product product = productRepository.findById(request.getProductId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found."));

        if (product.getOwner().getId().equals(claimant.getId())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Users cannot claim their own products.");
        }

        if (product.getStatus() == ProductStatus.CLOSED) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Closed products cannot be claimed.");
        }

        if (claimRepository.existsByClaimantIdAndProductIdAndStatus(
                claimant.getId(), product.getId(), ClaimStatus.PENDING)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Duplicate claim is not allowed.");
        }

        if (request.getRequestedQuantity() > product.getQuantity()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Requested quantity exceeds product quantity.");
        }
        if (product.getMaxClaimQuantity() != null
        && request.getRequestedQuantity() > product.getMaxClaimQuantity()) {
    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Requested quantity exceeds max claim quantity.");
}

        Claim claim = new Claim();
        claim.setClaimant(claimant);
        claim.setProduct(product);
        claim.setRequestedQuantity(request.getRequestedQuantity());
        claim.setStatus(ClaimStatus.PENDING);

        Claim saved = claimRepository.save(claim);

        refreshProductListingStatus(product);
        productRepository.save(product);

        return saved;
    }

    public List<Claim> getClaimsByProduct(Long productId) {
        return claimRepository.findByProductId(productId);
    }

    public List<Claim> getClaimsByClaimant(Long claimantId) {
        return claimRepository.findByClaimantId(claimantId);
    }

    @Transactional
    public Claim approveClaim(Long claimId) {
        Claim claim = claimRepository.findById(claimId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Claim not found."));

        if (claim.getStatus() != ClaimStatus.PENDING) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Only PENDING claims can be approved.");
        }

        Product product = claim.getProduct();
        if (claim.getRequestedQuantity() > product.getQuantity()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Insufficient product quantity.");
        }

        product.setQuantity(product.getQuantity() - claim.getRequestedQuantity());

        claim.setStatus(ClaimStatus.APPROVED);
        claimRepository.save(claim);

        refreshProductListingStatus(product);
        productRepository.save(product);

        return claim;
    }

    @Transactional
    public Claim rejectClaim(Long claimId) {
        Claim claim = claimRepository.findById(claimId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Claim not found."));

        if (claim.getStatus() != ClaimStatus.PENDING) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Only PENDING claims can be rejected.");
        }

        Product product = claim.getProduct();

        claim.setStatus(ClaimStatus.REJECTED);
        claimRepository.save(claim);

        refreshProductListingStatus(product);
        productRepository.save(product);

        return claim;
    }

    @Transactional
    public Claim withdrawClaim(Long claimId, WithdrawClaimRequest request) {
        User user = userRepository.findById(request.getClaimantId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Claimant not found."));
        if (user.getRole() != Role.NGO) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Only NGO users can withdraw claims.");
        }

        Claim claim = claimRepository.findById(claimId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Claim not found."));
        if (!claim.getClaimant().getId().equals(user.getId())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Not your claim.");
        }
        if (claim.getStatus() != ClaimStatus.PENDING) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Only PENDING claims can be withdrawn.");
        }

        Product product = claim.getProduct();
        claim.setStatus(ClaimStatus.WITHDRAWN);
        claimRepository.save(claim);

        refreshProductListingStatus(product);
        productRepository.save(product);

        return claim;
    }

    @Transactional
    public Claim patchClaim(Long claimId, PatchClaimRequest request) {
        User user = userRepository.findById(request.getClaimantId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Claimant not found."));
        if (user.getRole() != Role.NGO) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Only NGO users can edit claims.");
        }

        Claim claim = claimRepository.findById(claimId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Claim not found."));
        if (!claim.getClaimant().getId().equals(user.getId())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Not your claim.");
        }
        if (claim.getStatus() != ClaimStatus.PENDING) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Only PENDING claims can be edited.");
        }

        Product product = claim.getProduct();

if (product.getStatus() == ProductStatus.CLOSED) {
    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Closed products cannot be edited in claims.");
}

if (request.getRequestedQuantity() > product.getQuantity()) {
    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Requested quantity exceeds product quantity.");
}

if (product.getMaxClaimQuantity() != null
        && request.getRequestedQuantity() > product.getMaxClaimQuantity()) {
    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Requested quantity exceeds max claim quantity.");
}

claim.setRequestedQuantity(request.getRequestedQuantity());
        return claimRepository.save(claim);
    }

    /**
     * Recomputes product availability from inventory and pending claim count — does not change quantity except via callers.
     */
    private void refreshProductListingStatus(Product product) {
        int qty = product.getQuantity() != null ? product.getQuantity() : 0;
        long pendingCount = claimRepository.countByProductIdAndStatus(product.getId(), ClaimStatus.PENDING);

        if (qty <= 0) {
            product.setStatus(ProductStatus.CLOSED);
            return;
        }
        product.setStatus(pendingCount > 0 ? ProductStatus.PENDING : ProductStatus.AVAILABLE);
    }
}
