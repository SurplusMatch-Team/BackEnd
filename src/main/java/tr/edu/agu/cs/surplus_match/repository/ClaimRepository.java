package tr.edu.agu.cs.surplus_match.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import tr.edu.agu.cs.surplus_match.model.Claim;
import tr.edu.agu.cs.surplus_match.model.ClaimStatus;

import java.util.List;

public interface ClaimRepository extends JpaRepository<Claim, Long> {

    boolean existsByClaimantIdAndProductIdAndStatusIn(Long claimantId, Long productId, List<ClaimStatus> statuses);

    @Query("SELECT DISTINCT c FROM Claim c JOIN FETCH c.claimant cl LEFT JOIN FETCH cl.address JOIN FETCH c.product p JOIN FETCH p.owner WHERE p.owner.id = :ownerId")
    List<Claim> findByProductOwnerIdWithDetails(@Param("ownerId") Long ownerId);

    @Query("SELECT DISTINCT c FROM Claim c JOIN FETCH c.claimant cl LEFT JOIN FETCH cl.address JOIN FETCH c.product p JOIN FETCH p.owner WHERE c.claimant.id = :claimantId")
    List<Claim> findByClaimantIdWithDetails(@Param("claimantId") Long claimantId);

    List<Claim> findByProductId(Long productId);

    List<Claim> findByClaimantId(Long claimantId);

    List<Claim> findByProductOwnerId(Long ownerId);
}