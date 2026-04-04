package tr.edu.agu.cs.surplus_match.dto;

import lombok.Data;

@Data
public class LoginRequest {
    private String email;
    private String password;
}