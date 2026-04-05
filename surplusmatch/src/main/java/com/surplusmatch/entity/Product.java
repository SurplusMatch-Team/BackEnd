package com.surplusmatch.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private Integer quantity;

    @Column(name = "expiry_date")
    private LocalDateTime expiryDate;

    private String status; //"ACTIVE" "CLAIMED"

    // Foreign Key relationships

    @ManyToOne
    @JoinColumn(name = "users_id") // in MySQLForeign Key column name
    private User market;

    @ManyToOne
    @JoinColumn(name = "categories_id")
    private Category category;

    public Product() {}

    // Getter ve Setter Metotları
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public Integer getQuantity() { return quantity; }
    public void setQuantity(Integer quantity) { this.quantity = quantity; }
    public LocalDateTime getExpiryDate() { return expiryDate; }
    public void setExpiryDate(LocalDateTime expiryDate) { this.expiryDate = expiryDate; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public User getMarket() { return market; }
    public void setMarket(User market) { this.market = market; }
    public Category getCategory() { return category; }
    public void setCategory(Category category) { this.category = category; }
}