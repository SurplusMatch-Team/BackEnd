package tr.edu.agu.cs.surplus_match.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import tr.edu.agu.cs.surplus_match.model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    @Query("SELECT u FROM User u LEFT JOIN FETCH u.address WHERE u.email = :email")
    Optional<User> findByEmailWithAddress(@Param("email") String email);

}