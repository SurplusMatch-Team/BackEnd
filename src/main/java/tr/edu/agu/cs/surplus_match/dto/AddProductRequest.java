package tr.edu.agu.cs.surplus_match.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public class AddProductRequest {

    @NotBlank
    private String name;

    @NotNull
    @Min(1)
    private Integer quantity;

    @NotNull
    private LocalDateTime expiryDate;

    @NotNull
    private Long categoryId;

    @NotNull
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
