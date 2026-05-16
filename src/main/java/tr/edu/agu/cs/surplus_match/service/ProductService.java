package tr.edu.agu.cs.surplus_match.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tr.edu.agu.cs.surplus_match.dto.PatchProductRequest;
import tr.edu.agu.cs.surplus_match.model.Category;
import tr.edu.agu.cs.surplus_match.model.Product;
import tr.edu.agu.cs.surplus_match.model.ProductStatus;
import tr.edu.agu.cs.surplus_match.repository.CategoryRepository;
import tr.edu.agu.cs.surplus_match.repository.ProductRepository;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public ProductService(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
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
        product.setMaxClaimQuantity(request.getMaxClaimQuantity());
        product.setExpiryDate(request.getExpiryDate());
        product.setOwner(owner);
        product.setCategory(category);
        product.setStatus(ProductStatus.AVAILABLE);
        product.setUnit(request.getUnit() != null ? request.getUnit() : ProductUnit.UNIT);

        return productRepository.save(product);
    }

    public List<Product> getAllAvailableProducts() {
        return productRepository.findByStatus(ProductStatus.AVAILABLE);
    }

    public List<Product> getProductsByUserId(Long userId) {
        return productRepository.findByOwnerId(userId);
    }

    // Task 3: Edit product (PATCH)
    @Transactional
    public Product updateProduct(Long productId, PatchProductRequest request) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found!"));

        // Verify ownership
        if (request.getOwnerId() != null && !product.getOwner().getId().equals(request.getOwnerId())) {
            throw new RuntimeException("You are not the owner of this product.");
        }

        // Only update non-null fields (PATCH behavior)
        if (request.getName() != null) {
            product.setName(request.getName());
        }
        if (request.getDescription() != null) {
            product.setDescription(request.getDescription());
        }
        if (request.getQuantity() != null) {
            product.setQuantity(request.getQuantity());
        }
        if (request.getUnit() != null) {
            product.setUnit(request.getUnit());
        }
        if (request.getMaxClaimQuantity() != null) {
            product.setMaxClaimQuantity(request.getMaxClaimQuantity());
        }
        if (request.getMaxClaimQuantity() != null) {
    product.setMaxClaimQuantity(request.getMaxClaimQuantity());
}
        if (request.getExpiryDate() != null) {
            product.setExpiryDate(request.getExpiryDate());
        }
        if (request.getCategoryId() != null) {
            Category category = categoryRepository.findById(request.getCategoryId())
                    .orElseThrow(() -> new RuntimeException("Category not found!"));
            product.setCategory(category);
        }

        return productRepository.save(product);
    }

    // Task 3: Soft delete — set status CLOSED and quantity 0
    @Transactional
    public Product softDeleteProduct(Long productId, Long ownerId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found!"));

        if (!product.getOwner().getId().equals(ownerId)) {
            throw new RuntimeException("You are not the owner of this product.");
        }

        product.setStatus(ProductStatus.CLOSED);
        product.setQuantity(0);
        return productRepository.save(product);
    }
}
