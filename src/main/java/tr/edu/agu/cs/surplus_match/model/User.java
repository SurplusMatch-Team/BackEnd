package tr.edu.agu.cs.surplus_match.model;

import jakarta.persistence.*;
import java.util.List;
import java.util.ArrayList;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(name = "organization_name")
    private String organizationName;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    // Adres bağlantısı (Muhammet'ten gelen profesyonel ekleme)
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "address_id")
    private Address address;

    // Marketlerin eklediği ürünler (Senin isimlendirmenle: market -> products)
    @JsonIgnore // JSON çıktısında sonsuz döngüyü engeller, çok önemli!
    @OneToMany(mappedBy = "market", cascade = CascadeType.ALL)
    private List<Product> products = new ArrayList<>();

    // STK'ların yaptığı talepler (Muhammet'ten gelen ekleme)
    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<Claim> claims = new ArrayList<>();

    public User() {}

    // --- Getter ve Setter'lar ---
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getOrganizationName() { return organizationName; }
    public void setOrganizationName(String organizationName) { this.organizationName = organizationName; }

    public Role getRole() { return role; }
    public void setRole(Role role) { this.role = role; }

    public Address getAddress() { return address; }
    public void setAddress(Address address) { this.address = address; }

    public List<Product> getProducts() { return products; }
    public void setProducts(List<Product> products) { this.products = products; }

    public List<Claim> getClaims() { return claims; }
    public void setClaims(List<Claim> claims) { this.claims = claims; }
}