package tr.edu.agu.cs.surplus_match.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

/**
 * Carries the data required to add a new product.
 */
public class AddProductRequest {

    @NotBlank(message = "Product name is required.")
    private String name;

    @NotNull(message = "Quantity is required.")
    @Min(value = 1, message = "Quantity must be at least 1.")
    private Integer quantity;

    @NotNull(message = "Expiry date is required.")
    @Future(message = "Expiry date must be in the future.")
    private LocalDateTime expiryDate;

    @NotNull(message = "Category id is required.")
    private Long categoryId;

    @NotNull(message = "Owner id is required.")
    private Long ownerId;

    public AddProductRequest() {
    }

    public String getName() {
        return name;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public LocalDateTime getExpiryDate() {
        return expiryDate;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public void setExpiryDate(LocalDateTime expiryDate) {
        this.expiryDate = expiryDate;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }
}