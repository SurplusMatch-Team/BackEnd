package tr.edu.agu.cs.surplus_match.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tr.edu.agu.cs.surplus_match.dto.AuthResponse;
import tr.edu.agu.cs.surplus_match.dto.LoginRequest;
import tr.edu.agu.cs.surplus_match.dto.RegisterRequest;
import tr.edu.agu.cs.surplus_match.model.Address;
import tr.edu.agu.cs.surplus_match.model.Role;
import tr.edu.agu.cs.surplus_match.model.User;
import tr.edu.agu.cs.surplus_match.dto.AuthResponse.UserData;
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

    @Transactional
    public AuthResponse registerUser(RegisterRequest request) {
        String city = request.getCity() != null ? request.getCity().trim() : "";
        String district = request.getDistrict() != null ? request.getDistrict().trim() : "";
        String fullAddress = request.getFullAddress() != null ? request.getFullAddress().trim() : "";
        if (city.isEmpty() || district.isEmpty() || fullAddress.isEmpty()) {
            throw new IllegalArgumentException("City, district, and full address are required.");
        }

        User newUser = new User();
        newUser.setEmail(request.getEmail());
        newUser.setPassword(passwordEncoder.encode(request.getPassword()));
        newUser.setOrganizationName(
                request.getOrganizationName() != null && !request.getOrganizationName().isBlank()
                        ? request.getOrganizationName()
                        : "");

        if (request.getRole() != null && !request.getRole().isBlank()) {
            newUser.setRole(Role.valueOf(request.getRole().toUpperCase()));
        } else {
            newUser.setRole(Role.NGO);
        }

        Address address = new Address();
        address.setCity(city);
        address.setDistrict(district);
        address.setFullAddress(fullAddress);
        address.setUser(newUser);
        newUser.setAddress(address);

        userRepository.save(newUser);

        AuthResponse response = new AuthResponse();
        response.setMessage(newUser.getRole().name() + " registered successfully.");
        return response;
    }

    public AuthResponse loginUser(LoginRequest request) {
        AuthResponse response = new AuthResponse();

        Optional<User> userOptional = userRepository.findByEmailWithAddress(request.getEmail());

        if (userOptional.isPresent()) {
            User foundUser = userOptional.get();

            if (passwordEncoder.matches(request.getPassword(), foundUser.getPassword())) {
                response.setMessage("Login successful.");

                UserData data = new UserData();
                data.setId(foundUser.getId());
                data.setEmail(foundUser.getEmail());
                data.setRole(foundUser.getRole().name());
                copyAddressToUserData(foundUser.getAddress(), data);
                response.setUser(data);
                return response;
            }
        }

        response.setMessage("Invalid email or password.");
        return response;
    }

    private static void copyAddressToUserData(Address address, UserData data) {
        if (address == null || data == null) {
            return;
        }
        data.setCity(address.getCity());
        data.setDistrict(address.getDistrict());
        data.setFullAddress(address.getFullAddress());
    }
}
