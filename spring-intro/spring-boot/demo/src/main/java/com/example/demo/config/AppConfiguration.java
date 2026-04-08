package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class AppConfiguration {
    
    public PasswordEncoder getEncoder(){
        PasswordEncoder encoder = new BCryptPasswordEncoder(BCryptPasswordEncoder.BCryptVersion.$2A);
        return encoder;
    }

      @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
            // 1. Autorización de requests
            .authorizeHttpRequests(auth -> auth
                .requestMatchers(
                    "/css/**", "/js/**", "/images/**", "/webjars/**"
                ).permitAll() // recursos estáticos
                .requestMatchers("/login", "/error").permitAll() // páginas públicas
                .requestMatchers("/admin/**").hasRole("ADMIN") // rutas restringidas
                .anyRequest().authenticated() // todo lo demás requiere login
            )

            // 2. Configuración del login con Thymeleaf
            .formLogin(form -> form
                .loginPage("/login") // URL del controlador que devuelve login.html
                .loginProcessingUrl("/login") // endpoint que procesa el POST del login
                .defaultSuccessUrl("/home", true) // redirección tras login exitoso
                .failureUrl("/login?error=true") // si falla login
                .permitAll()
            )

            // 3. Logout
            .logout(logout -> logout
                .logoutUrl("/logout") // endpoint de logout
                .logoutSuccessUrl("/login?logout=true") // redirección tras logout
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
                .permitAll()
            )

            // 4. CSRF (importante para formularios en Thymeleaf)
            .csrf(csrf -> csrf
                .ignoringRequestMatchers("/h2-console/**") // opcional (solo desarrollo)
            )

            // 5. Configuración de headers (opcional para H2 console)
            .headers(headers -> headers
                .frameOptions(frame -> frame.disable())
            );

        return http.build();
    }
}

