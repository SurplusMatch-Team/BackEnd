package tr.edu.agu.cs.surplus_match.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Configures application security settings.
 */
@Configuration
public class SecurityConfig {

    /**
     * Defines the password encoder bean used to hash user passwords.
     *
     * @return password encoder
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Defines the security filter chain for HTTP requests.
     *
     * @param http the HTTP security object
     * @return configured security filter chain
     * @throws Exception if configuration fails
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // Disable CSRF for easier API testing during development.
                .csrf(csrf -> csrf.disable())

                // Allow all requests for now.
                // We will tighten these rules later when authentication is fully stabilized.
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/auth/**").permitAll()
                        .anyRequest().permitAll()
                )

                // Enable basic defaults where needed.
                .httpBasic(Customizer.withDefaults());

        return http.build();
    }
}