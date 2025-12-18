package com.conetdev.mydailydatabase.api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

//import com.conetdev.mydailydatabase.models.RazonSocial;
import com.conetdev.mydailydatabase.models.Usuario;
import com.conetdev.mydailydatabase.repository.UsuarioRepository;
import com.conetdev.mydailydatabase.utils.PasswordUtils;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    UsuarioRepository userRepository;

    @Autowired
    PasswordUtils passutil;

    private String class_name = UserController.class.getName();

    @PostMapping("/usuario/validar")
    ResponseEntity<Map<String, Object>> login(
            // public ResponseEntity<Usuario> login(
            @RequestParam("user") String user,
            @RequestParam("pass") String pass) {
        try {
            Optional<Usuario> entidad = userRepository.findByIdentidadUsuario(user);
            if (entidad.isPresent()) {
                Usuario u = entidad.get();
                if (passutil.matches(pass, u.getPasswordUsuario())) {
                    Map<String, Object> response = new HashMap<>();
                    response.put("status", "OK");
                    response.put("idusuario", u.getId());
                    response.put("nomusuario", u.getNombreUsuario() + " " + u.getApellidoUsuario());
                    response.put("roles", u.getRoles());

                    // response.put("idrazon", u.getCliente().getIdcliente());
                    // response.put("nomrazon", u.getCliente().getRazon_cliente());
                    Logger.getLogger(class_name).log(Level.INFO, "HttpStatus: OK, authentication successful");
                    return new ResponseEntity<>(response, HttpStatus.OK);
                } else {
                    Logger.getLogger(class_name).log(Level.SEVERE, "Error on query: {0}", "HttpStatus: UNAUTHORIZED");
                    return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
                }
            } else {
                Logger.getLogger(class_name).log(Level.SEVERE, "Empty query: {0}", "HttpStatus: NOT_FOUND");
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            Logger.getLogger(class_name).log(Level.SEVERE, "Error on validate(): {0}", e.toString());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/usuario")
    public ResponseEntity<List<Usuario>> getAll(@RequestParam(required = false) Long id) {
        try {
            List<Usuario> lista = new ArrayList<Usuario>();
            userRepository.findAll().forEach(lista::add);
            if (lista.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(lista, HttpStatus.OK);
        } catch (Exception e) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, "Error on getAll(): {0}", e.toString());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/usuario/{id}")
    public ResponseEntity<Usuario> getById(@PathVariable("id") Long id) {
        try {
            Optional<Usuario> entidad = userRepository.findById(id);
            if (entidad.isPresent()) {
                return new ResponseEntity<>(entidad.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, "Error on getById()", e.toString());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/usuario")
    public ResponseEntity<Usuario> create(@RequestBody Usuario entidad) {
        try {
            Usuario user = new Usuario();
            user.setTipoIdentidad(entidad.getTipoIdentidad());
            user.setNombreUsuario(entidad.getNombreUsuario());
            user.setApellidoUsuario(entidad.getApellidoUsuario());
            user.setIdentidadUsuario(entidad.getIdentidadUsuario());
            user.setCelularUsuario(entidad.getCelularUsuario());
            user.setPasswordUsuario(passutil.hash(entidad.getPasswordUsuario()));
            user.setEstado(entidad.getEstado());
            user.setRoles(entidad.getRoles());
            // user.setRazonesSociales(entidad.getRazonesSociales());
            Usuario _entidad = userRepository.save(user);
            return new ResponseEntity<>(_entidad, HttpStatus.CREATED);
        } catch (Exception e) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, "Error on create()", e.toString());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/usuario/{id}")
    public ResponseEntity<Usuario> update(@PathVariable("id") Long id, @RequestBody Usuario entidad) {
        try {
            Usuario _entidad = userRepository.findById(id).orElse(null);
            if (_entidad != null) {
                _entidad.setTipoIdentidad(entidad.getTipoIdentidad());
                _entidad.setNombreUsuario(entidad.getNombreUsuario());
                _entidad.setApellidoUsuario(entidad.getApellidoUsuario());
                _entidad.setIdentidadUsuario(entidad.getIdentidadUsuario());
                _entidad.setCelularUsuario(entidad.getCelularUsuario());
                _entidad.setEstado(entidad.getEstado());
                _entidad.setRoles(entidad.getRoles());
                // _entidad.setRazonesSociales(entidad.getRazonesSociales());
                _entidad.setEstado(entidad.getEstado());
                return new ResponseEntity<>(userRepository.save(_entidad), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, "Error on update(): ", e.toString());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/usuario/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") Long id) {
        try {
            userRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, "Error on delete(): ", e.toString());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/usuario/password/{id}")
    public ResponseEntity<HttpStatus> updatePassword(@PathVariable("id") Long id,
            @RequestBody Map<String, String> request) {
        try {
            String current = request.get("currentPassword");
            String newpass = request.get("newPassword");

            Usuario user = userRepository.findById(id).orElse(null);
            if (user == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            if (!passutil.matches(current, user.getPasswordUsuario())) {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED); // Or BAD_REQUEST
            }

            user.setPasswordUsuario(passutil.hash(newpass));
            userRepository.save(user);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, "Error on updatePassword(): ",
                    e.toString());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
