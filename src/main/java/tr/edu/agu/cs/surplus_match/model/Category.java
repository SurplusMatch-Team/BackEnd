package tr.edu.agu.cs.surplus_match.model;

<<<<<<< HEAD
import jakarta.persistence.*;

=======
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
>>>>>>> origin/muhammet
@Entity
@Table(name = "categories")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    public Category() {
    }

<<<<<<< HEAD
    // --- Getter ve Setter'lar ---
=======
>>>>>>> origin/muhammet
    public Long getId() {
        return id;
    }

<<<<<<< HEAD
    public void setId(Long id) {
        this.id = id;
    }

=======
>>>>>>> origin/muhammet
    public String getName() {
        return name;
    }

<<<<<<< HEAD
=======
    public void setId(Long id) {
        this.id = id;
    }

>>>>>>> origin/muhammet
    public void setName(String name) {
        this.name = name;
    }
}