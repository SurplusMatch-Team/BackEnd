package tr.edu.agu.cs.surplus_match.service;

import org.springframework.stereotype.Service;
import tr.edu.agu.cs.surplus_match.dto.CreateClaimRequest;
import tr.edu.agu.cs.surplus_match.model.Claim;
import tr.edu.agu.cs.surplus_match.model.ClaimStatus;
import tr.edu.agu.cs.surplus_match.model.Product;
import tr.edu.agu.cs.surplus_match.model.Role;
import tr.edu.agu.cs.surplus_match.model.User;
import tr.edu.agu.cs.surplus_match.repository.ClaimRepository;
import tr.edu.agu.cs.surplus_match.repository.ProductRepository;
import tr.edu.agu.cs.surplus_match.repository.UserRepository;

import java.util.List;

@Service
public class ClaimService {

    private final ClaimRepository claimRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    public ClaimService(ClaimRepository claimRepository,
                        UserRepository userRepository,
                        ProductRepository productRepository) {
        this.claimRepository = claimRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }

    /**
     * Creates a new claim after validating the business rules.
     *
     * @param request the claim creation request
     * @return the saved claim
     */
    public Claim createClaim(CreateClaimRequest request) {
        // Find the claimant user.
        User claimant = userRepository.findById(request.getClaimantId())
                .orElseThrow(() -> new IllegalArgumentException("Claimant user not found."));

        // Only NGO users are allowed to create claims.
        if (claimant.getRole() != Role.NGO) {
            throw new IllegalArgumentException("Only NGO users can create claims.");
        }

        // Find the product.
        Product product = productRepository.findById(request.getProductId())
                .orElseThrow(() -> new IllegalArgumentException("Product not found."));

        // A user cannot claim their own product.
        if (product.getOwner().getId().equals(claimant.getId())) {
            throw new IllegalArgumentException("You cannot create a claim for your own product.");
        }

        // Prevent duplicate claims from the same user for the same product.
        boolean alreadyClaimed = claimRepository.existsByClaimantIdAndProductId(
                claimant.getId(),
                product.getId()
        );

        if (alreadyClaimed) {
            throw new IllegalArgumentException("You have already created a claim for this product.");
        }

        // Create and save the claim.
        Claim claim = new Claim();
        claim.setClaimant(claimant);
        claim.setProduct(product);
        claim.setStatus(ClaimStatus.PENDING);

        return claimRepository.save(claim);
    }

    /**
     * Returns all claims created by a specific user.
     *
     * @param claimantId claimant user id
     * @return list of claims
     */
    public List<Claim> getClaimsByClaimant(Long claimantId) {
        return claimRepository.findByClaimantId(claimantId);
    }

    /**
     * Returns all claims created for a specific product.
     *
     * @param productId product id
     * @return list of claims
     */
    public List<Claim> getClaimsByProduct(Long productId) {
        return claimRepository.findByProductId(productId);
    }
}