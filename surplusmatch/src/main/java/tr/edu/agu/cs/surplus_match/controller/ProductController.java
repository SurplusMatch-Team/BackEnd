package tr.edu.agu.cs.surplus_match.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tr.edu.agu.cs.surplus_match.dto.AddProductRequest;
import tr.edu.agu.cs.surplus_match.model.Category;
import tr.edu.agu.cs.surplus_match.model.Product;
import tr.edu.agu.cs.surplus_match.model.Role;
import tr.edu.agu.cs.surplus_match.model.User;
import tr.edu.agu.cs.surplus_match.repository.CategoryRepository;
import tr.edu.agu.cs.surplus_match.repository.ProductRepository;
import tr.edu.agu.cs.surplus_match.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;

    public ProductController(ProductRepository productRepository,
                             UserRepository userRepository,
                             CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
    }

    @PostMapping("/add")
    public ResponseEntity<?> addProduct(@RequestBody AddProductRequest request) {

        Optional<User> marketOptional = userRepository.findById(request.getMarketId());
        if (marketOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Market user not found.");
        }

        User market = marketOptional.get();

        if (market.getRole() != Role.MARKET) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Only MARKET users can add products.");
        }

        Optional<Category> categoryOptional = categoryRepository.findById(request.getCategoryId());
        if (categoryOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Category not found.");
        }

        Category category = categoryOptional.get();

        Product product = new Product();
        product.setName(request.getName());
        product.setQuantity(request.getQuantity());
        product.setExpiryDate(request.getExpiryDate());
        product.setStatus(request.getStatus());
        product.setMarket(market);
        product.setCategory(category);

        Product savedProduct = productRepository.save(product);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedProduct);
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        return ResponseEntity.ok(productRepository.findAll());
    }
}