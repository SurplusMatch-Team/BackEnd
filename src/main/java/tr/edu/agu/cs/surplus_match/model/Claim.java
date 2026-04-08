package tr.edu.agu.cs.surplus_match.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "claims")
public class Claim {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "claim_date", nullable = false, updatable = false)
    private LocalDateTime claimDate;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    // Muhammet'in eklediği Enum yapısını kullanıyoruz (Daha profesyonel)
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ClaimStatus status;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "users_id", nullable = false) // Senin DB sütun isminle uyumlu bıraktık
    private User user;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "products_id", nullable = false) // Senin DB sütun isminle uyumlu bıraktık
    private Product product;

    public Claim() {
    }

    // Veri kaydedilmeden hemen önce tarihleri otomatik atar
    @PrePersist
    protected void onCreate() {
        this.claimDate = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
        if (this.status == null) {
            this.status = ClaimStatus.PENDING;
        }
    }

    // Veri her güncellendiğinde updatedAt tarihini yeniler
    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    // --- Getter ve Setter'lar ---
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public LocalDateTime getClaimDate() { return claimDate; }
    public void setClaimDate(LocalDateTime claimDate) { this.claimDate = claimDate; }

    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }

    public ClaimStatus getStatus() { return status; }
    public void setStatus(ClaimStatus status) { this.status = status; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public Product getProduct() { return product; }
    public void setProduct(Product product) { this.product = product; }
}