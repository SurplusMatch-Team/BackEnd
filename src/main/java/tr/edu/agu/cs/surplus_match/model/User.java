package tr.edu.agu.cs.surplus_match.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity // 1. Tells JPA to create a table for this class
@Table(name = "users")
@Data // 2. Lombok generates getters/setters
public class User {

    @Id // 3. Primary Key
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    private String role; // NGO, DONOR, etc.
}