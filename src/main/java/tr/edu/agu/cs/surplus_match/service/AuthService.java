package tr.edu.agu.cs.surplus_match.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
<<<<<<< HEAD
import tr.edu.agu.cs.surplus_match.dto.AuthResponse;
import tr.edu.agu.cs.surplus_match.dto.LoginRequest;
import tr.edu.agu.cs.surplus_match.dto.RegisterRequest;
=======
import tr.edu.agu.cs.surplus_match.dto.*;
>>>>>>> origin/muhammet
import tr.edu.agu.cs.surplus_match.model.Role;
import tr.edu.agu.cs.surplus_match.model.User;
import tr.edu.agu.cs.surplus_match.repository.UserRepository;

import java.util.Optional;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public AuthResponse registerUser(RegisterRequest request) {
        User newUser = new User();
        newUser.setEmail(request.getEmail());
<<<<<<< HEAD
        newUser.setPassword(passwordEncoder.encode(request.getPassword()));
        newUser.setOrganizationName(request.getOrganizationName());
        newUser.setRole(Role.valueOf(request.getRole().toUpperCase()));
=======

        // 🔒 SCRAMBLE: Never save the raw password!
        String encodedPassword = passwordEncoder.encode(request.getPassword());
        newUser.setPassword(encodedPassword);

        if (request.getRole() != null && !request.getRole().isBlank()) {
            newUser.setRole(Role.valueOf(request.getRole().toUpperCase()));
        } else {
            newUser.setRole(Role.NGO);
        }
>>>>>>> origin/muhammet

        userRepository.save(newUser);

        AuthResponse response = new AuthResponse();
        response.setMessage(request.getRole() + " registered successfully.");
        return response;
    }

    public AuthResponse loginUser(LoginRequest request) {
        AuthResponse response = new AuthResponse();

        Optional<User> userOptional = userRepository.findByEmail(request.getEmail());

        if (userOptional.isPresent()) {
            User foundUser = userOptional.get();

            if (passwordEncoder.matches(request.getPassword(), foundUser.getPassword())) {
                response.setMessage("Login successful.");

                AuthResponse.UserData data = new AuthResponse.UserData();
                data.setId(foundUser.getId());
                data.setEmail(foundUser.getEmail());
                data.setRole(foundUser.getRole().name());
                response.setUser(data);
                return response;
            }
        }

        response.setMessage("Invalid email or password.");
        return response;
    }
}