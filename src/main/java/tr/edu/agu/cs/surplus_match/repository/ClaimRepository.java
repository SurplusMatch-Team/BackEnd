package tr.edu.agu.cs.surplus_match.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tr.edu.agu.cs.surplus_match.model.Claim;

import java.util.List;

public interface ClaimRepository extends JpaRepository<Claim, Long> {

    List<Claim> findByProductId(Long productId);

    List<Claim> findByClaimantId(Long claimantId);

    boolean existsByClaimantIdAndProductId(Long claimantId, Long productId);
}
