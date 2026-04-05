package com.surplusmatch.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "addresses") // Java'daki Address ile MySQL'deki addresses'i bağlar
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String city;      // Kayseri
    private String district;  // Talas, Kocasinan vb.

    @Column(name = "full_address")
    private String fullAddress;

    @ManyToOne
    @JoinColumn(name = "users_id") // Bu adresin hangi kullanıcıya (markete/STK'ya) ait olduğu
    private User user;

    public Address() {}

    // --- Getter ve Setter'lar ---
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }

    public String getDistrict() { return district; }
    public void setDistrict(String district) { this.district = district; }

    public String getFullAddress() { return fullAddress; }
    public void setFullAddress(String fullAddress) { this.fullAddress = fullAddress; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
}