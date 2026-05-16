package tr.edu.agu.cs.surplus_match.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tr.edu.agu.cs.surplus_match.dto.AddProductRequest;
import tr.edu.agu.cs.surplus_match.dto.PatchProductRequest;
import tr.edu.agu.cs.surplus_match.model.Category;
import tr.edu.agu.cs.surplus_match.model.Product;
import tr.edu.agu.cs.surplus_match.model.Role;
import tr.edu.agu.cs.surplus_match.model.User;
import tr.edu.agu.cs.surplus_match.repository.CategoryRepository;
import tr.edu.agu.cs.surplus_match.repository.ProductRepository;
import tr.edu.agu.cs.surplus_match.repository.UserRepository;
import tr.edu.agu.cs.surplus_match.service.ProductService;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/products")
@CrossOrigin(origins = "*")
public class ProductController {

    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    private final ProductService productService;

    public ProductController(ProductRepository productRepository,
                             UserRepository userRepository,
                             CategoryRepository categoryRepository,
                             ProductService productService) {
        this.productRepository = productRepository;
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
        this.productService = productService;
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
        product.setOwner(market);
        product.setCategory(category);

        // New fields
        if (request.getDescription() != null) {
            product.setDescription(request.getDescription());
        }
        if (request.getUnit() != null) {
            product.setUnit(request.getUnit());
        }
        if (request.getMaxClaimQuantity() != null) {
            product.setMaxClaimQuantity(request.getMaxClaimQuantity());
        }

        Product savedProduct = productRepository.save(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedProduct);
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        return ResponseEntity.ok(productService.getAllAvailableProducts());
    }

    @GetMapping("/market/{userId}")
    public ResponseEntity<List<Product>> getMarketInventory(@PathVariable Long userId) {
        return ResponseEntity.ok(productService.getProductsByUserId(userId));
    }

    // Task 3: PATCH /api/products/{productId} — edit product
    @PatchMapping("/{productId}")
    public ResponseEntity<?> updateProduct(@PathVariable Long productId,
                                           @RequestBody PatchProductRequest request) {
        try {
            Product updated = productService.updateProduct(productId, request);
            return ResponseEntity.ok(updated);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // Task 3: DELETE /api/products/{productId} — soft delete
    @DeleteMapping("/{productId}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long productId,
                                           @RequestBody Map<String, Long> request) {
        try {
            Long ownerId = request.get("ownerId");
            Product deleted = productService.softDeleteProduct(productId, ownerId);
            return ResponseEntity.ok(deleted);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
