package tr.edu.agu.cs.surplus_match.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tr.edu.agu.cs.surplus_match.model.Product;
import tr.edu.agu.cs.surplus_match.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@CrossOrigin(origins = "*") // Frontend'in bağlanabilmesi için önemli!
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // URL: GET http://localhost:8080/api/products
    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }

    // URL: GET http://localhost:8080/api/products/market/1
    @GetMapping("/market/{userId}")
    public ResponseEntity<List<Product>> getMarketInventory(@PathVariable Long userId) {
        return ResponseEntity.ok(productService.getProductsByUserId(userId));
    }
}