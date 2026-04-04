package tr.edu.agu.cs.surplus_match.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tr.edu.agu.cs.surplus_match.dto.*;
import tr.edu.agu.cs.surplus_match.service.AuthService;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    // 1. We declare the Service we need
    private final AuthService authService;

    // 2. Constructor Injection: Spring automatically gives us an AuthService instance
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request) {
        // The Controller just passes the work to the Service
        return ResponseEntity.ok(authService.registerUser(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
        AuthResponse response = authService.loginUser(request);

        if (response.getMessage().contains("successful")) {
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.status(401).body(response);
    }
}