package tr.edu.agu.cs.surplus_match.dto;

import lombok.Data;

@Data // Bu bir "Lombok" büyüsüdür. Getter, Setter ve ToString'i otomatik yazar.
public class RegisterRequest {
    private String email;
    private String password;
    private String role; // "NGO" veya "MARKET"
}