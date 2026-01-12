package com.conetdev.mydailydatabase.service.impl;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Service;

import com.conetdev.mydailydatabase.model.Usuarios;
import com.conetdev.mydailydatabase.repository.UsuarioRepository;
import com.conetdev.mydailydatabase.request.LoginRequest;
import com.conetdev.mydailydatabase.service.AuthService;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UsuarioRepository userRepository;

    private final String class_name = AuthServiceImpl.class.getName();

    @Override
    public ResponseEntity<Map<String, Object>> login(LoginRequest loginRequest, HttpServletRequest request) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getUsername(),
                            loginRequest.getPassword()));

            if (authentication.isAuthenticated()) {
                SecurityContextHolder.getContext().setAuthentication(authentication);
                HttpSession session = request.getSession(true);
                session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY,
                        SecurityContextHolder.getContext());

                Optional<Usuarios> entidad = userRepository.findByIdentidadUsuario(loginRequest.getUsername());
                Usuarios u = entidad.get();
                session.setAttribute("id", u.getId());
                session.setAttribute("username", u.getIdentidadUsuario());
                session.setAttribute("fullname", u.getNombreUsuario() + " " + u.getApellidoUsuario());

                Map<String, Object> response = new HashMap<>();
                response.put("status", "OK");
                response.put("roles", u.getRoles());
                response.put("empresas", u.getRazonesSociales());

                Logger.getLogger(class_name).log(Level.INFO, "HttpStatus: OK, authentication successful");
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
                Logger.getLogger(class_name).log(Level.SEVERE,
                        "HttpStatus: NOT_FOUND, user not found or bad credentials");
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            Logger.getLogger(class_name).log(Level.SEVERE,
                    "HttpStatus: UNAUTHORIZED, authentication failed: " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    @Override
    public ResponseEntity<Map<String, Object>> setSessionUser(Map<String, String> payload, HttpServletRequest request) {
        try {
            String idrole = payload.get("idrol");
            String namerole = payload.get("nomrol");
            String idcompany = payload.get("idempresa");
            String namecompany = payload.get("nomempresa");
            HttpSession session = request.getSession(false);

            if (payload == null || payload.isEmpty()) {
                Logger.getLogger(class_name).log(Level.WARNING, "HttpStatus: BAD_REQUEST, payload is null or empty");
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }

            if (session == null || session.getAttribute("username") == null) {
                Logger.getLogger(class_name).log(Level.SEVERE, "HttpStatus: FORBIDDEN, session expired");
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }

            session.setAttribute("rolid", idrole);
            session.setAttribute("rolname", namerole);
            session.setAttribute("comid", idcompany);
            session.setAttribute("comname", namecompany);

            String target = switch (idrole) {
                case "1" -> "/administrator";
                case "2" -> "/manager";
                case "3" -> "/executive";
                case "4" -> "/project";
                case "5" -> "/asistant";
                case "6" -> "/projectmanager";
                case "7" -> "/storagekeeper";
                case "8" -> "/register";
                case "9" -> "/janitor";
                default -> "/home";
            };

            Map<String, Object> response = new HashMap<>();
            response.put("redirect", target);

            Logger.getLogger(class_name).log(Level.INFO, "HttpStatus: OK, role set successfully");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            Logger.getLogger(class_name).log(Level.SEVERE, "HttpStatus: UNAUTHORIZED, role set failed");
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    @Override
    public ResponseEntity<Map<String, Object>> getCurrentUser(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        try {
            if (session == null || session.getAttribute("username") == null) {
                Logger.getLogger(class_name).log(Level.SEVERE, "HttpStatus: FORBIDDEN, session expired");
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }
            Map<String, Object> response = new HashMap<>();
            response.put("username", session.getAttribute("username"));
            response.put("fullName", session.getAttribute("fullname"));
            response.put("roleId", session.getAttribute("rolid"));
            response.put("roleName", session.getAttribute("rolname"));
            response.put("companyId", session.getAttribute("comid"));
            response.put("companyName", session.getAttribute("comname"));

            Logger.getLogger(class_name).log(Level.INFO, "HttpStatus: OK, current user retrieved successfully");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            Logger.getLogger(class_name).log(Level.SEVERE, "HttpStatus: UNAUTHORIZED, current user retrieval failed");
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    @Override
    public ResponseEntity<Map<String, Object>> logout(HttpServletRequest request) {
        try {
            HttpSession session = request.getSession(false);
            if (session != null) {
                session.invalidate();
            }
            SecurityContextHolder.clearContext();
            Map<String, Object> response = new HashMap<>();
            response.put("status", "OK");

            Logger.getLogger(class_name).log(Level.INFO, "HttpStatus: OK, Close session successfully");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            Logger.getLogger(class_name).log(Level.SEVERE, "HttpStatus: UNAUTHORIZED, Close session failed");
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }
}
