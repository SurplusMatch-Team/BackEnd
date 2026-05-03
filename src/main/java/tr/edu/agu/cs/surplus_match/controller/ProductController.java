package tr.edu.agu.cs.surplus_match.controller;

<<<<<<< HEAD
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
import tr.edu.agu.cs.surplus_match.service.ProductService;

import java.util.List;
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

        Product savedProduct = productRepository.save(product);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedProduct);
=======
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tr.edu.agu.cs.surplus_match.dto.AddProductRequest;
import tr.edu.agu.cs.surplus_match.model.Product;
import tr.edu.agu.cs.surplus_match.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<Product> addProduct(@Valid @RequestBody AddProductRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.addProduct(request));
>>>>>>> origin/muhammet
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
<<<<<<< HEAD
        return ResponseEntity.ok(productService.getAllAvailableProducts());
    }

    @GetMapping("/market/{userId}")
    public ResponseEntity<List<Product>> getMarketInventory(@PathVariable Long userId) {
        return ResponseEntity.ok(productService.getProductsByUserId(userId));
    }
}
=======
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @GetMapping("/owner/{ownerId}")
    public ResponseEntity<List<Product>> getProductsByOwner(@PathVariable Long ownerId) {
        return ResponseEntity.ok(productService.getProductsByOwner(ownerId));
    }

    @GetMapping("/urgent")
    public ResponseEntity<List<Product>> getUrgentProducts() {
        return ResponseEntity.ok(productService.getUrgentProducts());
    }
}
>>>>>>> origin/muhammet
