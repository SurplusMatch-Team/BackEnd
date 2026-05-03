package tr.edu.agu.cs.surplus_match.model;

<<<<<<< HEAD
import jakarta.persistence.*;
import java.time.LocalDateTime;

=======
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

import java.time.LocalDateTime;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
>>>>>>> origin/muhammet
@Entity
@Table(name = "claims")
public class Claim {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

<<<<<<< HEAD
    @Column(name = "claim_date", nullable = false, updatable = false)
    private LocalDateTime claimDate;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "requested_quantity", nullable = false)
=======
    @Column(nullable = false, updatable = false)
    private LocalDateTime claimDate;

    @Column(nullable = false)
>>>>>>> origin/muhammet
    private Integer requestedQuantity;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ClaimStatus status;

<<<<<<< HEAD
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "users_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "products_id", nullable = false)
    private Product product;

    public Claim() {}

    public Claim(User user, Product product, ClaimStatus status) {
        this.user = user;
        this.product = product;
        this.status = status;
    }

    public Claim(User user, Product product, Integer requestedQuantity, ClaimStatus status) {
        this.user = user;
        this.product = product;
        this.requestedQuantity = requestedQuantity;
        this.status = status;
=======
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "claimant_id", nullable = false)
    private User claimant;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    public Claim() {
>>>>>>> origin/muhammet
    }

    @PrePersist
    protected void onCreate() {
        this.claimDate = LocalDateTime.now();
<<<<<<< HEAD
        this.updatedAt = LocalDateTime.now();
=======
>>>>>>> origin/muhammet
        if (this.status == null) {
            this.status = ClaimStatus.PENDING;
        }
    }

<<<<<<< HEAD
    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    // --- Getters and Setters ---
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public LocalDateTime getClaimDate() { return claimDate; }
    public void setClaimDate(LocalDateTime claimDate) { this.claimDate = claimDate; }

    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }

    public Integer getRequestedQuantity() { return requestedQuantity; }
    public void setRequestedQuantity(Integer requestedQuantity) { this.requestedQuantity = requestedQuantity; }

    public ClaimStatus getStatus() { return status; }
    public void setStatus(ClaimStatus status) { this.status = status; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public Product getProduct() { return product; }
    public void setProduct(Product product) { this.product = product; }
=======
    public Long getId() {
        return id;
    }

    public LocalDateTime getClaimDate() {
        return claimDate;
    }

    public Integer getRequestedQuantity() {
        return requestedQuantity;
    }

    public ClaimStatus getStatus() {
        return status;
    }

    public User getClaimant() {
        return claimant;
    }

    public Product getProduct() {
        return product;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setClaimDate(LocalDateTime claimDate) {
        this.claimDate = claimDate;
    }

    public void setRequestedQuantity(Integer requestedQuantity) {
        this.requestedQuantity = requestedQuantity;
    }

    public void setStatus(ClaimStatus status) {
        this.status = status;
    }

    public void setClaimant(User claimant) {
        this.claimant = claimant;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
>>>>>>> origin/muhammet
}