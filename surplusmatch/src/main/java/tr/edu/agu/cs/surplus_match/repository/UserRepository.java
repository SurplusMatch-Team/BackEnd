package tr.edu.agu.cs.surplus_match.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tr.edu.agu.cs.surplus_match.model.User;

import java.util.Optional;

/**
 * Handles database operations for User entities.
 */
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Finds a user by email address.
     *
     * @param email the email to search for
     * @return an optional user
     */
    Optional<User> findByEmail(String email);
}