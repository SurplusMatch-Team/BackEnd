package tr.edu.agu.cs.surplus_match.service;

<<<<<<< HEAD
import org.springframework.stereotype.Service;
import tr.edu.agu.cs.surplus_match.model.Product;
import tr.edu.agu.cs.surplus_match.model.ProductStatus;
import tr.edu.agu.cs.surplus_match.repository.ProductRepository;
=======
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import tr.edu.agu.cs.surplus_match.dto.AddProductRequest;
import tr.edu.agu.cs.surplus_match.model.Category;
import tr.edu.agu.cs.surplus_match.model.Product;
import tr.edu.agu.cs.surplus_match.model.ProductStatus;
import tr.edu.agu.cs.surplus_match.model.Role;
import tr.edu.agu.cs.surplus_match.model.User;
import tr.edu.agu.cs.surplus_match.repository.CategoryRepository;
import tr.edu.agu.cs.surplus_match.repository.ProductRepository;
import tr.edu.agu.cs.surplus_match.repository.UserRepository;
>>>>>>> origin/muhammet

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;
<<<<<<< HEAD

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
=======
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;

    public ProductService(ProductRepository productRepository,
                          UserRepository userRepository,
                          CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
    }

    public Product addProduct(AddProductRequest request) {
        User owner = userRepository.findById(request.getOwnerId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Owner not found."));

        if (owner.getRole() != Role.MARKET) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Only MARKET users can add products.");
        }

        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found."));

        Product product = new Product();
        product.setName(request.getName());
        product.setQuantity(request.getQuantity());
        product.setExpiryDate(request.getExpiryDate());
        product.setOwner(owner);
        product.setCategory(category);
        product.setStatus(ProductStatus.AVAILABLE);

        return productRepository.save(product);
    }

    public List<Product> getAllProducts() {
        return productRepository.findAllByOrderByCreatedAtDesc();
    }

    public List<Product> getProductsByOwner(Long ownerId) {
        return productRepository.findByOwnerId(ownerId);
    }

    public List<Product> getUrgentProducts() {
        return productRepository.findByStatusAndQuantityGreaterThanOrderByExpiryDateAsc(ProductStatus.AVAILABLE, 0);
    }
}
>>>>>>> origin/muhammet
