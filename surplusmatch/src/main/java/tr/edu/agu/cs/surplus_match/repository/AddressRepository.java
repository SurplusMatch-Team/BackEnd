package tr.edu.agu.cs.surplus_match.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tr.edu.agu.cs.surplus_match.model.Address;

/**
 * Handles database operations for Address entities.
 */
public interface AddressRepository extends JpaRepository<Address, Long> {
}