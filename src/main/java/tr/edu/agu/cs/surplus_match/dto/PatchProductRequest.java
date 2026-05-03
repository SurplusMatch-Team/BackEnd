package tr.edu.agu.cs.surplus_match.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import tr.edu.agu.cs.surplus_match.model.ProductUnit;

import java.time.LocalDateTime;

/** Partial update: only non-null fields are applied. {@code ownerId} authorizes MARKET ownership. */
public class PatchProductRequest {

    /**
     * Market user performing the edit. JSON may send {@code marketId}.
     */
    @NotNull
    @JsonAlias("marketId")
    private Long ownerId;

    private String name;

    /** When supplied, updates stock (0 closes listing — same as DELETE). */
    @PositiveOrZero
    private Integer quantity;

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

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
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
