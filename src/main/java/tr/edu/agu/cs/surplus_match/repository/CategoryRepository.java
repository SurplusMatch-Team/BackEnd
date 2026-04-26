package tr.edu.agu.cs.surplus_match.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tr.edu.agu.cs.surplus_match.model.Category;

<<<<<<< HEAD
public interface CategoryRepository extends JpaRepository<Category, Long> {
=======
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    Optional<Category> findByName(String name);
>>>>>>> 34fecc9a64ef8f73c1b78acf14c272b8999724cc
}