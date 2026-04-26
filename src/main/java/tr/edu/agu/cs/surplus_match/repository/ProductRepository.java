package tr.edu.agu.cs.surplus_match.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tr.edu.agu.cs.surplus_match.model.Product;
<<<<<<< HEAD

public interface ProductRepository extends JpaRepository<Product, Long> {
=======
import tr.edu.agu.cs.surplus_match.model.ProductStatus; // Enum importu önemli!
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    // Buraya bu satırı eklemezsen Service hata verir:
    List<Product> findByStatus(ProductStatus status);
    
    List<Product> findByOwnerId(Long ownerId);
>>>>>>> 34fecc9a64ef8f73c1b78acf14c272b8999724cc
}