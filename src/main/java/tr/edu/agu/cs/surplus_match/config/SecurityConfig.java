package tr.edu.agu.cs.surplus_match.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.Customizer;

@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                // Use global MVC CORS config from CorsConfig
                .cors(Customizer.withDefaults())
                // 2. CSRF'i devre dışı bırak (API testleri için şart)
                .csrf(csrf -> csrf.disable())
                // 3. Tüm isteklere izin ver (Şimdilik kapıları açtık)
                .authorizeHttpRequests(auth -> auth.anyRequest().permitAll())
                // 4. H2 Console için frame izni
                .headers(headers -> headers.frameOptions(frame -> frame.sameOrigin()));

        return http.build();
    }
}