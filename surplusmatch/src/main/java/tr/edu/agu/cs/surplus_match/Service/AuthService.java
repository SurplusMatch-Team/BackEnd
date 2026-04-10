package tr.edu.agu.cs.surplus_match.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import tr.edu.agu.cs.surplus_match.dto.AuthResponse;
import tr.edu.agu.cs.surplus_match.dto.LoginRequest;
import tr.edu.agu.cs.surplus_match.dto.RegisterRequest;
import tr.edu.agu.cs.surplus_match.model.User;
import tr.edu.agu.cs.surplus_match.repository.UserRepository;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Registers a new user after validating basic business rules.
     *
     * @param request the registration request
     * @return a clean authentication response
     */
    public AuthResponse register(RegisterRequest request) {
        // Check whether the email is already used by another user.
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new IllegalArgumentException("This email is already registered.");
        }

        // Create a new user entity and fill its fields from the request.
        User user = new User();
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setOrganizationName(request.getOrganizationName());
        user.setRole(request.getRole());

        // Save the new user into the database.
        User savedUser = userRepository.save(user);

        // Build and return a safe response object.
        return buildAuthResponse("User registered successfully.", savedUser);
    }

    /**
     * Authenticates a user by checking email and password.
     *
     * @param request the login request
     * @return a clean authentication response
     */
    public AuthResponse login(LoginRequest request) {
        // Find the user by email.
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("Invalid email or password."));

        // Compare the raw password with the encoded password in the database.
        boolean passwordMatches = passwordEncoder.matches(request.getPassword(), user.getPassword());

        if (!passwordMatches) {
            throw new IllegalArgumentException("Invalid email or password.");
        }

        return buildAuthResponse("Login successful.", user);
    }

    /**
     * Creates a safe response object from a user entity.
     *
     * @param message response message
     * @param user    authenticated user
     * @return authentication response
     */
    private AuthResponse buildAuthResponse(String message, User user) {
        AuthResponse response = new AuthResponse();
        response.setMessage(message);

        AuthResponse.UserSummary userSummary = new AuthResponse.UserSummary();
        userSummary.setId(user.getId());
        userSummary.setEmail(user.getEmail());
        userSummary.setOrganizationName(user.getOrganizationName());
        userSummary.setRole(user.getRole());

        response.setUser(userSummary);
        return response;
    }
}