package tr.edu.agu.cs.surplus_match.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import tr.edu.agu.cs.surplus_match.model.ProductUnit;

import java.time.LocalDateTime;

public class PatchProductRequest {

    @NotNull
    @JsonAlias("marketId")
    private Long ownerId;

    private String name;
    private String description;

    @PositiveOrZero
    private Integer quantity;

    @PositiveOrZero
    private Integer maxClaimQuantity;

    private LocalDateTime expiryDate;
    private Long categoryId;
    private ProductUnit unit;

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getMaxClaimQuantity() {
        return maxClaimQuantity;
    }

    public void setMaxClaimQuantity(Integer maxClaimQuantity) {
        this.maxClaimQuantity = maxClaimQuantity;
    }

    public LocalDateTime getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(LocalDateTime expiryDate) {
        this.expiryDate = expiryDate;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public ProductUnit getUnit() {
        return unit;
    }

    public void setUnit(ProductUnit unit) {
        this.unit = unit;
    }
}
