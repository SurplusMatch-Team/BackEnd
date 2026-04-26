package tr.edu.agu.cs.surplus_match.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import tr.edu.agu.cs.surplus_match.dto.*;
import tr.edu.agu.cs.surplus_match.model.User;
import tr.edu.agu.cs.surplus_match.repository.UserRepository;
import tr.edu.agu.cs.surplus_match.model.Role;

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
        newUser.setPassword(passwordEncoder.encode(request.getPassword()));
        newUser.setOrganizationName(request.getOrganizationName());
        newUser.setRole(Role.valueOf(request.getRole().toUpperCase()));

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