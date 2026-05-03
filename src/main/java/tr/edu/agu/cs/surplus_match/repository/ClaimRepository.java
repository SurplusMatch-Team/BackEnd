package tr.edu.agu.cs.surplus_match.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tr.edu.agu.cs.surplus_match.model.Claim;
import tr.edu.agu.cs.surplus_match.model.ClaimStatus;

import java.util.List;

public interface ClaimRepository extends JpaRepository<Claim, Long> {

    List<Claim> findByProductId(Long productId);

    List<Claim> findByClaimantId(Long claimantId);

    /** True if this NGO already has an active pending claim on that product */
    boolean existsByClaimantIdAndProductIdAndStatus(Long claimantId, Long productId, ClaimStatus status);

    long countByProductIdAndStatus(Long productId, ClaimStatus status);
}
