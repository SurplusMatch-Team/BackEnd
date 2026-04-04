package tr.edu.agu.cs.surplus_match.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import tr.edu.agu.cs.surplus_match.dto.*;
import tr.edu.agu.cs.surplus_match.model.User;
import tr.edu.agu.cs.surplus_match.repository.UserRepository;

import java.util.Optional;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder; // The Scrambler

    // Constructor Injection - Both must be here for Spring to work!
    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // 1. SECURE REGISTER LOGIC
    public AuthResponse registerUser(RegisterRequest request) {
        User newUser = new User();
        newUser.setEmail(request.getEmail());

        // 🔒 SCRAMBLE: Never save the raw password!
        String encodedPassword = passwordEncoder.encode(request.getPassword());
        newUser.setPassword(encodedPassword);

        newUser.setRole("NGO"); // Default role for now

        userRepository.save(newUser);

        AuthResponse response = new AuthResponse();
        response.setMessage("User registered with a SECURE hashed password!");
        return response;
    }

    // 2. SECURE LOGIN LOGIC
    public AuthResponse loginUser(LoginRequest request) {
        AuthResponse response = new AuthResponse();
        Optional<User> userOptional = userRepository.findByEmail(request.getEmail());

        if (userOptional.isPresent()) {
            User foundUser = userOptional.get();

            // 🔒 MATCH: Use .matches() because we can't "un-scramble" the hash
            if (passwordEncoder.matches(request.getPassword(), foundUser.getPassword())) {
                response.setMessage("Login successful with secure check!");

                AuthResponse.UserData data = new AuthResponse.UserData();
                data.setId(foundUser.getId());
                data.setEmail(foundUser.getEmail());
                data.setRole(foundUser.getRole());
                response.setUser(data);
                return response;
            }
        }

        response.setMessage("Invalid email or password!");
        return response;
    }
}