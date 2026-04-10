package tr.edu.agu.cs.surplus_match.dto;

import tr.edu.agu.cs.surplus_match.model.Role;

/**
 * Represents the authentication response returned after login or registration.
 */
public class AuthResponse {

    private String message;
    private UserSummary user;

    public AuthResponse() {
    }

    public String getMessage() {
        return message;
    }

    public UserSummary getUser() {
        return user;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setUser(UserSummary user) {
        this.user = user;
    }

    /**
     * Represents a safe summary of the authenticated user.
     */
    public static class UserSummary {

        private Long id;
        private String email;
        private String organizationName;
        private Role role;

        public UserSummary() {
        }

        public Long getId() {
            return id;
        }

        public String getEmail() {
            return email;
        }

        public String getOrganizationName() {
            return organizationName;
        }

        public Role getRole() {
            return role;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public void setOrganizationName(String organizationName) {
            this.organizationName = organizationName;
        }

        public void setRole(Role role) {
            this.role = role;
        }
    }
}