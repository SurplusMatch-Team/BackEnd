package tr.edu.agu.cs.surplus_match.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tr.edu.agu.cs.surplus_match.model.*;
import tr.edu.agu.cs.surplus_match.repository.*;

import java.util.Arrays;
import java.util.List;

@Service
public class ClaimService {

    private final ClaimRepository claimRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    public ClaimService(ClaimRepository claimRepository, ProductRepository productRepository, UserRepository userRepository) {
        this.claimRepository = claimRepository;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public Claim createClaim(Long claimantId, Long productId, Integer requestedQuantity) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found!"));

        User claimant = userRepository.findById(claimantId)
                .orElseThrow(() -> new RuntimeException("User not found!"));

        if (product.getStatus() != ProductStatus.AVAILABLE) {
            throw new RuntimeException("Product is not available for claiming.");
        }

        boolean duplicate = claimRepository.existsByClaimantIdAndProductIdAndStatusIn(
                claimantId, productId, Arrays.asList(ClaimStatus.PENDING, ClaimStatus.APPROVED));
        if (duplicate) {
            throw new RuntimeException("You already have an active claim on this product.");
        }

        if (requestedQuantity == null || requestedQuantity <= 0) {
            throw new RuntimeException("Requested quantity must be greater than 0.");
        }
        if (requestedQuantity > product.getQuantity()) {
            throw new RuntimeException("Requested quantity exceeds available stock.");
        }
        if (product.getMaxClaimQuantity() != null && requestedQuantity > product.getMaxClaimQuantity()) {
            throw new RuntimeException("Requested quantity exceeds the maximum claim limit of " + product.getMaxClaimQuantity() + ".");
        }

        product.setStatus(ProductStatus.PENDING);
        productRepository.save(product);

        Claim claim = new Claim(claimant, product, requestedQuantity, ClaimStatus.PENDING);
        return claimRepository.save(claim);
    }

    @Transactional
    public Claim createClaim(Long claimantId, Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found!"));
        return createClaim(claimantId, productId, product.getQuantity());
    }

    @Transactional
    public Claim editClaim(Long claimId, Integer newQuantity) {
        Claim claim = claimRepository.findById(claimId)
                .orElseThrow(() -> new RuntimeException("Claim not found!"));

        if (claim.getStatus() != ClaimStatus.PENDING) {
            throw new RuntimeException("Only PENDING claims can be edited.");
        }

        Product product = claim.getProduct();

        if (newQuantity == null || newQuantity <= 0) {
            throw new RuntimeException("Requested quantity must be greater than 0.");
        }
        if (newQuantity > product.getQuantity()) {
            throw new RuntimeException("Requested quantity exceeds available stock.");
        }
        if (product.getMaxClaimQuantity() != null && newQuantity > product.getMaxClaimQuantity()) {
            throw new RuntimeException("Requested quantity exceeds the maximum claim limit.");
        }

        claim.setRequestedQuantity(newQuantity);
        return claimRepository.save(claim);
    }

    @Transactional
    public Claim withdrawClaim(Long claimId) {
        Claim claim = claimRepository.findById(claimId)
                .orElseThrow(() -> new RuntimeException("Claim not found!"));

        if (claim.getStatus() != ClaimStatus.PENDING) {
            throw new RuntimeException("Only PENDING claims can be withdrawn.");
        }

        claim.setStatus(ClaimStatus.WITHDRAWN);

        Product product = claim.getProduct();
        List<Claim> pendingClaims = claimRepository.findByProductId(product.getId())
                .stream()
                .filter(c -> c.getStatus() == ClaimStatus.PENDING && !c.getId().equals(claimId))
                .toList();
        if (pendingClaims.isEmpty()) {
            product.setStatus(ProductStatus.AVAILABLE);
            productRepository.save(product);
        }

        return claimRepository.save(claim);
    }

    @Transactional
    public Claim approveClaim(Long claimId) {
        Claim claim = claimRepository.findById(claimId)
                .orElseThrow(() -> new RuntimeException("Claim not found!"));

        if (claim.getStatus() != ClaimStatus.PENDING) {
            throw new RuntimeException("Only PENDING claims can be approved.");
        }

        claim.setStatus(ClaimStatus.APPROVED);

        Product product = claim.getProduct();
        int remaining = product.getQuantity() - claim.getRequestedQuantity();
        product.setQuantity(Math.max(remaining, 0));
        if (product.getQuantity() == 0) {
            product.setStatus(ProductStatus.CLOSED);
        } else {
            product.setStatus(ProductStatus.AVAILABLE);
        }
        productRepository.save(product);

        return claimRepository.save(claim);
    }

    @Transactional
    public Claim rejectClaim(Long claimId) {
        Claim claim = claimRepository.findById(claimId)
                .orElseThrow(() -> new RuntimeException("Claim not found!"));

        if (claim.getStatus() != ClaimStatus.PENDING) {
            throw new RuntimeException("Only PENDING claims can be rejected.");
        }

        claim.setStatus(ClaimStatus.REJECTED);

        Product product = claim.getProduct();
        List<Claim> pendingClaims = claimRepository.findByProductId(product.getId())
                .stream()
                .filter(c -> c.getStatus() == ClaimStatus.PENDING && !c.getId().equals(claimId))
                .toList();
        if (pendingClaims.isEmpty()) {
            product.setStatus(ProductStatus.AVAILABLE);
            productRepository.save(product);
        }

        return claimRepository.save(claim);
    }

    public List<Claim> getClaimsByProductOwner(Long ownerId) {
        return claimRepository.findByProductOwnerId(ownerId);
    }

    public List<Claim> getClaimsByClaimant(Long claimantId) {
        return claimRepository.findByClaimantId(claimantId);
    }
}