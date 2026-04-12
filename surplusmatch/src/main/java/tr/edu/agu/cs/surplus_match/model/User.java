package tr.edu.agu.cs.surplus_match.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a user in the system.
 * A user can be either a MARKET or an NGO.
 */
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

    /**
     * Stores the products owned by this user.
     * Prevents infinite JSON recursion.
     */
    @JsonIgnore
    @OneToMany(mappedBy = "owner", fetch = FetchType.LAZY)
    private List<Product> ownedProducts = new ArrayList<>();

    /**
     * Stores the claims created by this user.
     * Prevents infinite JSON recursion.
     */
    @JsonIgnore
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

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public Role getRole() {
        return role;
    }

    public List<Product> getOwnedProducts() {
        return ownedProducts;
    }

    public List<Claim> getCreatedClaims() {
        return createdClaims;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public void setOwnedProducts(List<Product> ownedProducts) {
        this.ownedProducts = ownedProducts;
    }

    public void setCreatedClaims(List<Claim> createdClaims) {
        this.createdClaims = createdClaims;
    }
}