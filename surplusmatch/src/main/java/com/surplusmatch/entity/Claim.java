package com.surplusmatch.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "claims")
public class Claim {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "claim_date")
    private LocalDateTime claimDate;

    private String status; // "PENDING", "RECEIVED", "CANCELLED"

    @ManyToOne
    @JoinColumn(name = "users_id") // Talebi yapan STK
    private User user;

    @ManyToOne
    @JoinColumn(name = "products_id") // Talep edilen ürün
    private Product product;

    public Claim() {}

    // Getter ve Setter'lar
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public LocalDateTime getClaimDate() { return claimDate; }
    public void setClaimDate(LocalDateTime claimDate) { this.claimDate = claimDate; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
    public Product getProduct() { return product; }
    public void setProduct(Product product) { this.product = product; }
}