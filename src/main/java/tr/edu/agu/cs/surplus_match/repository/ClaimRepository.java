package tr.edu.agu.cs.surplus_match.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tr.edu.agu.cs.surplus_match.model.Claim;
import tr.edu.agu.cs.surplus_match.model.ClaimStatus;

import java.util.List;

public interface ClaimRepository extends JpaRepository<Claim, Long> {

    boolean existsByClaimantIdAndProductIdAndStatusIn(Long claimantId, Long productId, List<ClaimStatus> statuses);

    List<Claim> findByProductId(Long productId);

    List<Claim> findByClaimantId(Long claimantId);

    List<Claim> findByProductOwnerId(Long ownerId);
}