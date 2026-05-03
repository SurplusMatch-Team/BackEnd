package tr.edu.agu.cs.surplus_match.dto;

public class RegisterRequest {

    private String email;
    private String password;
<<<<<<< HEAD
    private String organizationName;
=======
>>>>>>> origin/muhammet
    private String role;

    public RegisterRequest() {
    }

    public String getEmail() {
        return email;
    }

<<<<<<< HEAD
    public void setEmail(String email) {
        this.email = email;
    }
    
=======
>>>>>>> origin/muhammet
    public String getPassword() {
        return password;
    }

<<<<<<< HEAD
    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

=======
>>>>>>> origin/muhammet
    public String getRole() {
        return role;
    }

<<<<<<< HEAD
=======
    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

>>>>>>> origin/muhammet
    public void setRole(String role) {
        this.role = role;
    }
}