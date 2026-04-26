package tr.edu.agu.cs.surplus_match.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tr.edu.agu.cs.surplus_match.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
}