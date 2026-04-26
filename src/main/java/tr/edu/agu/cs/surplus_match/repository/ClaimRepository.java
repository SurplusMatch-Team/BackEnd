package tr.edu.agu.cs.surplus_match.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tr.edu.agu.cs.surplus_match.model.Claim;

public interface ClaimRepository extends JpaRepository<Claim, Long> {
}