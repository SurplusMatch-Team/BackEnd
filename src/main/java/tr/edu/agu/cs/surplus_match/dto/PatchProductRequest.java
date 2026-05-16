package tr.edu.agu.cs.surplus_match.dto;

import java.time.LocalDateTime;

public class PatchProductRequest {

    private String name;
    private String description;
    private Integer quantity;
    private String unit;
    private Integer maxClaimQuantity;
    private LocalDateTime expiryDate;
    private Long categoryId;
    private Long ownerId;

    public PatchProductRequest() {}

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public Integer getQuantity() { return quantity; }
    public void setQuantity(Integer quantity) { this.quantity = quantity; }

    public String getUnit() { return unit; }
    public void setUnit(String unit) { this.unit = unit; }

    public Integer getMaxClaimQuantity() { return maxClaimQuantity; }
    public void setMaxClaimQuantity(Integer maxClaimQuantity) { this.maxClaimQuantity = maxClaimQuantity; }

    public LocalDateTime getExpiryDate() { return expiryDate; }
    public void setExpiryDate(LocalDateTime expiryDate) { this.expiryDate = expiryDate; }

    public Long getCategoryId() { return categoryId; }
    public void setCategoryId(Long categoryId) { this.categoryId = categoryId; }

    public Long getOwnerId() { return ownerId; }
    public void setOwnerId(Long ownerId) { this.ownerId = ownerId; }
}
