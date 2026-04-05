package tr.edu.agu.cs.surplus_match.dto;

import lombok.Data;

@Data
public class AuthResponse {
    private String message;
    private UserData user;

    @Data
    public static class UserData {
        private Long id;
        private String email;
        private String role;
    }
}