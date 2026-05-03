package tr.edu.agu.cs.surplus_match.dto;

<<<<<<< HEAD
=======
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

>>>>>>> origin/muhammet
import java.time.LocalDateTime;

public class AddProductRequest {

<<<<<<< HEAD
    private String name;
    private Integer quantity;
    private LocalDateTime expiryDate;
    private String status;
    private Long categoryId;
    private Long marketId;
=======
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
>>>>>>> origin/muhammet

    public AddProductRequest() {
    }

    public String getName() {
        return name;
    }

<<<<<<< HEAD
    public void setName(String name) {
        this.name = name;
    }

=======
>>>>>>> origin/muhammet
    public Integer getQuantity() {
        return quantity;
    }

<<<<<<< HEAD
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

=======
>>>>>>> origin/muhammet
    public LocalDateTime getExpiryDate() {
        return expiryDate;
    }

<<<<<<< HEAD
    public void setExpiryDate(LocalDateTime expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

=======
>>>>>>> origin/muhammet
    public Long getCategoryId() {
        return categoryId;
    }

<<<<<<< HEAD
=======
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

>>>>>>> origin/muhammet
    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

<<<<<<< HEAD
    public Long getMarketId() {
        return marketId;
    }

    public void setMarketId(Long marketId) {
        this.marketId = marketId;
    }
}
=======
    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }
}
>>>>>>> origin/muhammet
