package tr.edu.agu.cs.surplus_match.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import tr.edu.agu.cs.surplus_match.dto.AddProductRequest;
import tr.edu.agu.cs.surplus_match.dto.DeleteProductRequest;
import tr.edu.agu.cs.surplus_match.dto.PatchProductRequest;
import tr.edu.agu.cs.surplus_match.model.Category;
import tr.edu.agu.cs.surplus_match.model.Product;
import tr.edu.agu.cs.surplus_match.model.ProductStatus;
import tr.edu.agu.cs.surplus_match.model.ProductUnit;
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
        product.setUnit(request.getUnit() != null ? request.getUnit() : ProductUnit.UNIT);

        return productRepository.save(product);
    }

    public List<Product> getAllAvailableProducts() {
        return productRepository.findByStatus(ProductStatus.AVAILABLE);
    }

    public List<Product> getProductsByOwner(Long ownerId) {
        return productRepository.findByOwnerId(ownerId);
    }

    public List<Product> getUrgentProducts() {
        return productRepository.findByStatusAndQuantityGreaterThanOrderByExpiryDateAsc(ProductStatus.AVAILABLE, 0);
    }

    @Transactional
    public Product patchProduct(Long productId, PatchProductRequest request) {
        User market = userRepository.findById(request.getOwnerId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found."));

        if (market.getRole() != Role.MARKET) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Only MARKET users can edit products.");
        }

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found."));

        if (!product.getOwner().getId().equals(market.getId())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Not the owner of this product.");
        }

        if (request.getName() != null && !request.getName().isBlank()) {
            product.setName(request.getName());
        }
        if (request.getQuantity() != null) {
            if (request.getQuantity() <= 0) {
                product.setQuantity(0);
                product.setStatus(ProductStatus.CLOSED);
            } else {
                product.setQuantity(request.getQuantity());
            }
        }
        if (request.getExpiryDate() != null) {
            product.setExpiryDate(request.getExpiryDate());
        }
        if (request.getCategoryId() != null) {
            Category category = categoryRepository.findById(request.getCategoryId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found."));
            product.setCategory(category);
        }
        if (request.getUnit() != null) {
            product.setUnit(request.getUnit());
        }

        return productRepository.save(product);
    }

    @Transactional
    public void softDeleteProduct(Long productId, DeleteProductRequest request) {
        User market = userRepository.findById(request.getOwnerId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found."));

        if (market.getRole() != Role.MARKET) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Only MARKET users can delete products.");
        }

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found."));

        if (!product.getOwner().getId().equals(market.getId())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Not the owner of this product.");
        }

        product.setQuantity(0);
        product.setStatus(ProductStatus.CLOSED);
        productRepository.save(product);
    }
}
