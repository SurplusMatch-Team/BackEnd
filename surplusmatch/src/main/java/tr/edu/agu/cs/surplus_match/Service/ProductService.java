package tr.edu.agu.cs.surplus_match.service;

import org.springframework.stereotype.Service;
import tr.edu.agu.cs.surplus_match.dto.AddProductRequest;
import tr.edu.agu.cs.surplus_match.model.Category;
import tr.edu.agu.cs.surplus_match.model.Product;
import tr.edu.agu.cs.surplus_match.model.ProductStatus;
import tr.edu.agu.cs.surplus_match.model.Role;
import tr.edu.agu.cs.surplus_match.model.User;
import tr.edu.agu.cs.surplus_match.repository.CategoryRepository;
import tr.edu.agu.cs.surplus_match.repository.ProductRepository;
import tr.edu.agu.cs.surplus_match.repository.UserRepository;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;

    public ProductService(ProductRepository productRepository,
                          UserRepository userRepository,
                          CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
    }

    /**
     * Creates a new product after validating owner and category rules.
     *
     * @param request the product creation request
     * @return the saved product
     */
    public Product addProduct(AddProductRequest request) {
        // Find the owner user.
        User owner = userRepository.findById(request.getOwnerId())
                .orElseThrow(() -> new IllegalArgumentException("Owner user not found."));

        // Only MARKET users are allowed to add products.
        if (owner.getRole() != Role.MARKET) {
            throw new IllegalArgumentException("Only MARKET users can add products.");
        }

        // Find the category.
        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new IllegalArgumentException("Category not found."));

        // Create the product entity.
        Product product = new Product();
        product.setName(request.getName());
        product.setQuantity(request.getQuantity());
        product.setExpiryDate(request.getExpiryDate());
        product.setOwner(owner);
        product.setCategory(category);

        // The backend decides the initial status.
        product.setStatus(ProductStatus.AVAILABLE);

        return productRepository.save(product);
    }

    /**
     * Returns all products in the system.
     *
     * @return list of products
     */
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    /**
     * Returns all products owned by a specific market.
     *
     * @param ownerId owner user id
     * @return list of products
     */
    public List<Product> getProductsByOwner(Long ownerId) {
        return productRepository.findByOwnerId(ownerId);
    }
}