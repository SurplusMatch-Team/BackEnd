package tr.edu.agu.cs.surplus_match.service;

import org.springframework.stereotype.Service;
import tr.edu.agu.cs.surplus_match.model.Product;
import tr.edu.agu.cs.surplus_match.repository.ProductRepository;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    // Tüm ürünleri getir (Genel liste için)
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    // Sadece belli bir marketin (user) ürünlerini getir (Caner'in istediği envanter)
    public List<Product> getProductsByUserId(Long userId) {
    return productRepository.findByOwnerId(userId);
}
}