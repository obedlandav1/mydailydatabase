package com.conetdev.mydailydatabase.service;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import com.conetdev.mydailydatabase.request.LoginRequest;
import java.util.Map;

public interface AuthService {

    ResponseEntity<Map<String, Object>> login(LoginRequest loginRequest, HttpServletRequest request);

    ResponseEntity<Map<String, Object>> setSessionUser(Map<String, String> payload, HttpServletRequest request);

    ResponseEntity<Map<String, Object>> getCurrentUser(HttpServletRequest request);

    ResponseEntity<Map<String, Object>> logout(HttpServletRequest request);
}
