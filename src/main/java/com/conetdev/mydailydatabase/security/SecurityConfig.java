package com.conetdev.mydailydatabase.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity; // Importante
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

        // 1. EXPONER EL AUTHENTICATION MANAGER (Vital para tu API de Login)
        @Bean
        public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
                return authConfig.getAuthenticationManager();
        }

        @Bean
        public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
                http
                                // Deshabilitar CSRF temporalmente para facilitar las pruebas con Axios
                                .csrf(Customizer.withDefaults())

                                .authorizeHttpRequests(auth -> auth
                                                // 1. Permitir login, logout y recursos públicos (CSS, JS, imágenes,
                                                // etc.)
                                                .requestMatchers(
                                                                "/api/v1/auth/login",
                                                                "/api/v1/auth/logout",
                                                                "/login",
                                                                "/error",
                                                                "/layouts/**",
                                                                "/views/**",
                                                                "/css/**",
                                                                "/fonts/**",
                                                                "/img/**",
                                                                "/js/**",
                                                                "/svg/**")
                                                .permitAll()
                                                // 2. Cualquier otra API requiere que el usuario esté logueado
                                                .requestMatchers("/api/v1/**").authenticated()
                                                // 3. El resto de la web también (vistas de Thymeleaf)
                                                .anyRequest().authenticated())

                                // Gestión de sesión explícita
                                .securityContext(securityContext -> securityContext
                                                .securityContextRepository(new HttpSessionSecurityContextRepository()))

                                // Configuración de formulario básica (fallback)
                                .formLogin(form -> form
                                                .loginPage("/login")
                                                .permitAll())

                                .logout(logout -> logout
                                                .logoutUrl("/logout")
                                                .deleteCookies("JSESSIONID")
                                                .permitAll());

                return http.build();
        }

        @Bean
        public PasswordEncoder passwordEncoder() {
                return new BCryptPasswordEncoder(10);
        }
}