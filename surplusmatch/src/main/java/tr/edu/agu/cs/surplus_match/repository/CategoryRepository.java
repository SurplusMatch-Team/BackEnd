package tr.edu.agu.cs.surplus_match.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tr.edu.agu.cs.surplus_match.model.Category;

/**
 * Handles database operations for Category entities.
 */
public interface CategoryRepository extends JpaRepository<Category, Long> {
}