package tr.edu.agu.cs.surplus_match.service;

import org.springframework.stereotype.Service;
import tr.edu.agu.cs.surplus_match.model.Product;
import tr.edu.agu.cs.surplus_match.model.ProductStatus;
import tr.edu.agu.cs.surplus_match.repository.ProductRepository;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllAvailableProducts() {
    // Repository'de tanımladığımız metodu çağırıyoruz
    return productRepository.findByStatus(ProductStatus.AVAILABLE);
}

    // Sadece belli bir marketin (user) ürünlerini getir (Caner'in istediği envanter)
    public List<Product> getProductsByUserId(Long userId) {
    return productRepository.findByOwnerId(userId);
}
}