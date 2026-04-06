package tr.edu.agu.cs.surplus_match.model;

import jakarta.persistence.*;

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

    @Enumerated(EnumType.STRING) // İŞTE HATAYI ÇÖZEN SATIR!
    @Column(nullable = false)
    private Role role; 

    public User() {}

    // Getter ve Setter'lar
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public String getOrganizationName() { return organizationName; }
    public void setOrganizationName(String organizationName) { this.organizationName = organizationName; }
    public Role getRole() { return role; }
    public void setRole(Role role) { this.role = role;  }
}