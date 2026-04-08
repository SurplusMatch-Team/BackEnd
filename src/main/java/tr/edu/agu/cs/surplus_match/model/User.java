package tr.edu.agu.cs.surplus_match.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

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

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "address_id", unique = true)
    private Address address;

    @OneToMany(mappedBy = "owner", fetch = FetchType.LAZY)
    private List<Product> ownedProducts = new ArrayList<>();

    @OneToMany(mappedBy = "claimant", fetch = FetchType.LAZY)
    private List<Claim> createdClaims = new ArrayList<>();

    public User() {
    }

    public User(String email, String password, String organizationName, Role role) {
        this.email = email;
        this.password = password;
        this.organizationName = organizationName;
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<Product> getOwnedProducts() {
        return ownedProducts;
    }

    public void setOwnedProducts(List<Product> ownedProducts) {
        this.ownedProducts = ownedProducts;
    }

    public List<Claim> getCreatedClaims() {
        return createdClaims;
    }

    public void setCreatedClaims(List<Claim> createdClaims) {
        this.createdClaims = createdClaims;
    }
}