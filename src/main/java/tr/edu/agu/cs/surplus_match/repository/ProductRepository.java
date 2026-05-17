package tr.edu.agu.cs.surplus_match.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import tr.edu.agu.cs.surplus_match.model.Product;
import tr.edu.agu.cs.surplus_match.model.ProductStatus;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("SELECT DISTINCT p FROM Product p JOIN FETCH p.owner o LEFT JOIN FETCH o.address WHERE p.status = :status")
    List<Product> findByStatusWithOwnerAddress(@Param("status") ProductStatus status);

    @Query("SELECT DISTINCT p FROM Product p JOIN FETCH p.owner o LEFT JOIN FETCH o.address WHERE o.id = :ownerId")
    List<Product> findByOwnerIdWithOwnerAddress(@Param("ownerId") Long ownerId);

    List<Product> findByStatus(ProductStatus status);

    List<Product> findByOwnerId(Long ownerId);

    List<Product> findByStatusAndQuantityGreaterThanOrderByExpiryDateAsc(ProductStatus status, Integer quantity);
}
