package com.conetdev.mydailydatabase.api.v1;

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
import org.springframework.web.bind.annotation.*;

import com.conetdev.mydailydatabase.repository.UsuarioRepository;
import com.conetdev.mydailydatabase.request.LoginRequest;
import com.conetdev.mydailydatabase.model.Usuarios;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UsuarioRepository userRepository;

    private String class_name = AuthController.class.getName();

    // PASO 1: Login inicial (Solo valida credenciales)
    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody LoginRequest loginRequest,
            HttpServletRequest request) {

        try {
            // Validar credenciales
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getUsername(),
                            loginRequest.getPassword()));
            if (authentication.isAuthenticated()) {
                // Crear la Sesión manualmente (Stateful)
                SecurityContextHolder.getContext().setAuthentication(authentication);
                HttpSession session = request.getSession(true);
                session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY,
                        SecurityContextHolder.getContext());

                // Guardar el username en sesión de una vez
                Optional<Usuarios> entidad = userRepository.findByIdentidadUsuario(loginRequest.getUsername());
                Usuarios u = entidad.get();
                session.setAttribute("id", u.getId());
                session.setAttribute("username", u.getIdentidadUsuario());
                session.setAttribute("fullname", u.getNombreUsuario() + " " + u.getApellidoUsuario());

                // Responder al Frontend con los roles para que el usuario elija
                Map<String, Object> response = new HashMap<>();
                response.put("status", "OK");
                response.put("roles", u.getRoles());

                Logger.getLogger(class_name).log(Level.INFO, "HttpStatus: OK, authentication successful");
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
                Logger.getLogger(class_name).log(Level.SEVERE,
                        "HttpStatus: NOT_FOUND, user not found or bad credentials");
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

        } catch (Exception e) {
            Logger.getLogger(class_name).log(Level.SEVERE, "HttpStatus: UNAUTHORIZED, authentication failed");
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    @PostMapping("/rol")
    public ResponseEntity<Map<String, Object>> setRole(@RequestBody Map<String, String> payload,
            HttpServletRequest request) {

        try {
            String idrole = payload.get("idrol");
            String namerole = payload.get("nomrol"); // Ej: "ROLE_ADMINISTRADOR DE SISTEMA"
            HttpSession session = request.getSession(false); // Recuperamos la sesión existente

            if (payload == null || payload.isEmpty()) {
                Logger.getLogger(class_name).log(Level.WARNING, "HttpStatus: BAD_REQUEST, payload is null or empty");
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST); // Error 400
            }

            // Validar que hay sesión
            if (session == null || session.getAttribute("username") == null) {
                Logger.getLogger(class_name).log(Level.SEVERE, "HttpStatus: FORBIDDEN, session expired");
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }

            // 1. Guardar el rol elegido en la Variable de Sesión
            session.setAttribute("rolid", idrole);
            session.setAttribute("rolnom", namerole);

            // 2. Determinar la URL (Tu lógica original movida aquí)
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

            // 3. Devolver la URL al frontend
            Map<String, Object> response = new HashMap<>();
            response.put("redirect", target);

            Logger.getLogger(class_name).log(Level.INFO, "HttpStatus: OK, role set successfully");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            Logger.getLogger(class_name).log(Level.SEVERE, "HttpStatus: UNAUTHORIZED, role set failed");
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping("/current-user")
    public ResponseEntity<Map<String, Object>> getCurrentUser(HttpServletRequest request) {
        HttpSession session = request.getSession(false); // No crear nueva, solo leer

        try {
            // Validar que hay sesión
            if (session == null || session.getAttribute("username") == null) {
                Logger.getLogger(class_name).log(Level.SEVERE, "HttpStatus: FORBIDDEN, session expired");
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }
            Map<String, Object> response = new HashMap<>();
            // response.put("id", session.getAttribute("id"));
            response.put("username", session.getAttribute("username")); // identidad
            response.put("fullName", session.getAttribute("fullname")); // nombre completo
            response.put("roleId", session.getAttribute("rolid"));
            response.put("roleName", session.getAttribute("rolnom"));

            Logger.getLogger(class_name).log(Level.INFO, "HttpStatus: OK, current user retrieved successfully");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            Logger.getLogger(class_name).log(Level.SEVERE, "HttpStatus: UNAUTHORIZED, role set failed");
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    @PostMapping("/logout")
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
