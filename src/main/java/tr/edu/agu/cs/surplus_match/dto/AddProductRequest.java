package tr.edu.agu.cs.surplus_match.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import tr.edu.agu.cs.surplus_match.model.ProductUnit;

import java.time.LocalDateTime;

public class AddProductRequest {

    @NotBlank
    private String name;

    private String description;

    @NotNull
    @Min(1)
    private Integer quantity;

    @Min(1)
    private Integer maxClaimQuantity;

    @NotNull
    private LocalDateTime expiryDate;

    @NotNull
    private Long categoryId;

    @NotNull
    private Long marketId;

    private ProductUnit unit;

    public AddProductRequest() {
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

    public Long getMarketId() {
        return marketId;
    }

    public void setMarketId(Long marketId) {
        this.marketId = marketId;
    }

    public ProductUnit getUnit() {
        return unit;
    }

    public void setUnit(ProductUnit unit) {
        this.unit = unit;
    }
}
