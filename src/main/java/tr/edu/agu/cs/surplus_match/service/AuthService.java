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
    private final PasswordEncoder passwordEncoder;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // ✅ GERÇEK KAYIT (REGISTER)
    public AuthResponse registerUser(RegisterRequest request) {
        User newUser = new User();
        newUser.setEmail(request.getEmail());

        // 🔒 Şifreyi şifrele (BCrypt)
        newUser.setPassword(passwordEncoder.encode(request.getPassword()));

        // ✨ DİNAMİK ROL: Caner ne gönderirse o kaydedilir
        newUser.setRole(request.getRole());

        userRepository.save(newUser); // Veritabanına (SQL) yazma anı

        AuthResponse response = new AuthResponse();
        response.setMessage(request.getRole() + " kaydı başarıyla SQL veritabanına eklendi!");
        return response;
    }

    // ✅ GERÇEK GİRİŞ (LOGIN)
    public AuthResponse loginUser(LoginRequest request) {
        AuthResponse response = new AuthResponse();

        // Veritabanında e-posta ile ara
        Optional<User> userOptional = userRepository.findByEmail(request.getEmail());

        if (userOptional.isPresent()) {
            User foundUser = userOptional.get();

            // 🔒 Şifre kontrolü (Hashed vs Plain)
            if (passwordEncoder.matches(request.getPassword(), foundUser.getPassword())) {
                response.setMessage("Giriş başarılı! Hoş geldin, " + foundUser.getRole());

                AuthResponse.UserData data = new AuthResponse.UserData();
                data.setId(foundUser.getId());
                data.setEmail(foundUser.getEmail());
                data.setRole(foundUser.getRole());
                response.setUser(data);
                return response;
            }
        }

        response.setMessage("Hatalı e-posta veya şifre!");
        return response;
    }
}