package tr.edu.agu.cs.surplus_match.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tr.edu.agu.cs.surplus_match.model.Product;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    
    /**
     * Market sahibi kendi envanterini görmek istediğinde 
     * veritabanında user_id sütununa göre filtreleme yapar.
     */
    List<Product> findByOwnerId(Long ownerId);
}