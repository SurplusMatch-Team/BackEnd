package tr.edu.agu.cs.surplus_match.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tr.edu.agu.cs.surplus_match.dto.AddProductRequest;
import tr.edu.agu.cs.surplus_match.dto.DeleteProductRequest;
import tr.edu.agu.cs.surplus_match.dto.PatchProductRequest;
import tr.edu.agu.cs.surplus_match.model.Product;
import tr.edu.agu.cs.surplus_match.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@CrossOrigin(origins = "*")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    /** Accept POST to root or legacy {@code /add} path. */
    @PostMapping({"", "/add"})
    public ResponseEntity<Product> addProduct(@Valid @RequestBody AddProductRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.addProduct(request));
    }

    /** Public listing of items available for claiming. */
    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        return ResponseEntity.ok(productService.getAllAvailableProducts());
    }

    @GetMapping("/owner/{ownerId}")
    public ResponseEntity<List<Product>> getProductsByOwner(@PathVariable Long ownerId) {
        return ResponseEntity.ok(productService.getProductsByOwner(ownerId));
    }

    /** Alias for legacy clients using {@code /market/{userId}}. */
    @GetMapping("/market/{userId}")
    public ResponseEntity<List<Product>> getMarketInventory(@PathVariable Long userId) {
        return ResponseEntity.ok(productService.getProductsByOwner(userId));
    }

    @GetMapping("/urgent")
    public ResponseEntity<List<Product>> getUrgentProducts() {
        return ResponseEntity.ok(productService.getUrgentProducts());
    }

    @PatchMapping("/{productId}")
    public ResponseEntity<Product> patchProduct(@PathVariable Long productId,
                                                @Valid @RequestBody PatchProductRequest body) {
        return ResponseEntity.ok(productService.patchProduct(productId, body));
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<Void> softDeleteProduct(@PathVariable Long productId,
                                                   @Valid @RequestBody DeleteProductRequest body) {
        productService.softDeleteProduct(productId, body);
        return ResponseEntity.noContent().build();
    }
}
