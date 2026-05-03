package tr.edu.agu.cs.surplus_match.dto;

public class AuthResponse {

    private String message;
    private UserData user;

    public AuthResponse() {
    }

    public String getMessage() {
        return message;
    }

<<<<<<< HEAD
    public void setMessage(String message) {
        this.message = message;
    }

=======
>>>>>>> origin/muhammet
    public UserData getUser() {
        return user;
    }

<<<<<<< HEAD
=======
    public void setMessage(String message) {
        this.message = message;
    }

>>>>>>> origin/muhammet
    public void setUser(UserData user) {
        this.user = user;
    }

    public static class UserData {
        private Long id;
        private String email;
        private String role;

        public UserData() {
        }

        public Long getId() {
            return id;
        }

<<<<<<< HEAD
        public void setId(Long id) {
            this.id = id;
        }

=======
>>>>>>> origin/muhammet
        public String getEmail() {
            return email;
        }

<<<<<<< HEAD
        public void setEmail(String email) {
            this.email = email;
        }

=======
>>>>>>> origin/muhammet
        public String getRole() {
            return role;
        }

<<<<<<< HEAD
=======
        public void setId(Long id) {
            this.id = id;
        }

        public void setEmail(String email) {
            this.email = email;
        }

>>>>>>> origin/muhammet
        public void setRole(String role) {
            this.role = role;
        }
    }
}