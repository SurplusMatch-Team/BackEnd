package tr.edu.agu.cs.surplus_match.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tr.edu.agu.cs.surplus_match.model.Product;

import java.util.List;

/**
 * Handles database operations for Product entities.
 */
public interface ProductRepository extends JpaRepository<Product, Long> {

    /**
     * Finds all products owned by a specific user.
     *
     * @param ownerId the owner user id
     * @return list of products
     */
    List<Product> findByOwnerId(Long ownerId);

    /**
     * Finds all products belonging to a specific category.
     *
     * @param categoryId the category id
     * @return list of products
     */
    List<Product> findByCategoryId(Long categoryId);
}