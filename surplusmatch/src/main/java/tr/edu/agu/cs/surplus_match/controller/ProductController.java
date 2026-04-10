package tr.edu.agu.cs.surplus_match.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tr.edu.agu.cs.surplus_match.dto.AddProductRequest;
import tr.edu.agu.cs.surplus_match.model.Product;
import tr.edu.agu.cs.surplus_match.service.ProductService;

import java.util.List;

/**
 * Handles product-related endpoints.
 */
@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    /**
     * Creates a new product for a MARKET user.
     *
     * @param request the product creation request body
     * @return the created product
     */
    @PostMapping
    public ResponseEntity<Product> addProduct(@Valid @RequestBody AddProductRequest request) {
        Product savedProduct = productService.addProduct(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedProduct);
    }

    /**
     * Returns all products in the system.
     *
     * @return list of all products
     */
    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        return ResponseEntity.ok(products);
    }

    /**
     * Returns all products created by a specific owner.
     *
     * @param ownerId the owner user id
     * @return list of owner products
     */
    @GetMapping("/owner/{ownerId}")
    public ResponseEntity<List<Product>> getProductsByOwner(@PathVariable Long ownerId) {
        List<Product> products = productService.getProductsByOwner(ownerId);
        return ResponseEntity.ok(products);
    }
}