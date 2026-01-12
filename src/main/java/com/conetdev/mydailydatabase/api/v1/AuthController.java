package com.conetdev.mydailydatabase.api.v1;

import com.conetdev.mydailydatabase.service.AuthService;
import com.conetdev.mydailydatabase.request.LoginRequest;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import jakarta.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    private String class_name = AuthController.class.getName();

    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody LoginRequest loginRequest,
            HttpServletRequest request) {
        Logger.getLogger(class_name).log(Level.INFO, "Petición de login para: " + loginRequest.getUsername());
        return authService.login(loginRequest, request);
    }

    @PostMapping("/session-user")
    public ResponseEntity<Map<String, Object>> setSessionUser(@RequestBody Map<String, String> payload,
            HttpServletRequest request) {
        Logger.getLogger(class_name).log(Level.INFO, "Fijando datos de sesión");
        return authService.setSessionUser(payload, request);
    }

    @GetMapping("/current-user")
    public ResponseEntity<Map<String, Object>> getCurrentUser(HttpServletRequest request) {
        Logger.getLogger(class_name).log(Level.INFO, "Obteniendo usuario actual");
        return authService.getCurrentUser(request);
    }

    @PostMapping("/logout")
    public ResponseEntity<Map<String, Object>> logout(HttpServletRequest request) {
        Logger.getLogger(class_name).log(Level.INFO, "Petición de logout");
        return authService.logout(request);
    }

}
