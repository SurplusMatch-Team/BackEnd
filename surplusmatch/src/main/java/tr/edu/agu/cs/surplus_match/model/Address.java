package tr.edu.agu.cs.surplus_match.model;

import jakarta.persistence.*;

/**
 * Represents an address in the system.
 */
@Entity
@Table(name = "addresses")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String city;

    private String district;

    @Column(name = "full_address")
    private String fullAddress;

    public Address() {
    }

    public Long getId() {
        return id;
    }

    public String getCity() {
        return city;
    }

    public String getDistrict() {
        return district;
    }

    public String getFullAddress() {
        return fullAddress;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public void setFullAddress(String fullAddress) {
        this.fullAddress = fullAddress;
    }
}