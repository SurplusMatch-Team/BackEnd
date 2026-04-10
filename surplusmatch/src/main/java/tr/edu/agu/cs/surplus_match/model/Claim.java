package tr.edu.agu.cs.surplus_match.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

/**
 * Represents a claim created by an NGO user for a product.
 */
@Entity
@Table(name = "claims")
public class Claim {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "claim_date", nullable = false, updatable = false)
    private LocalDateTime claimDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ClaimStatus status;

    /**
     * The user who created this claim.
     */
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "claimant_id", nullable = false)
    private User claimant;

    /**
     * The product targeted by this claim.
     */
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    public Claim() {
    }

    @PrePersist
    protected void onCreate() {
        this.claimDate = LocalDateTime.now();

        if (this.status == null) {
            this.status = ClaimStatus.PENDING;
        }
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getClaimDate() {
        return claimDate;
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

    public void setStatus(ClaimStatus status) {
        this.status = status;
    }

    public void setClaimant(User claimant) {
        this.claimant = claimant;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}