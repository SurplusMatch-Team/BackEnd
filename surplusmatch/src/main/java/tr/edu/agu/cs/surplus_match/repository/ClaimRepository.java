package tr.edu.agu.cs.surplus_match.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tr.edu.agu.cs.surplus_match.model.Claim;

import java.util.List;

/**
 * Handles database operations for Claim entities.
 */
public interface ClaimRepository extends JpaRepository<Claim, Long> {

    /**
     * Finds all claims created by a specific user.
     *
     * @param claimantId the claimant user id
     * @return list of claims
     */
    List<Claim> findByClaimantId(Long claimantId);

    /**
     * Finds all claims created for a specific product.
     *
     * @param productId the product id
     * @return list of claims
     */
    List<Claim> findByProductId(Long productId);

    /**
     * Checks whether a specific user already created a claim for a specific product.
     *
     * @param claimantId the claimant user id
     * @param productId the product id
     * @return true if such a claim exists
     */
    boolean existsByClaimantIdAndProductId(Long claimantId, Long productId);
}