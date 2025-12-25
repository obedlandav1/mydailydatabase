package com.conetdev.mydailydatabase.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.conetdev.mydailydatabase.api.v1.UserController;
import com.conetdev.mydailydatabase.dto.UsuarioRequest;
import com.conetdev.mydailydatabase.model.Usuario;
import com.conetdev.mydailydatabase.mapper.UsuarioMapper;
import com.conetdev.mydailydatabase.repository.UsuarioRepository;
import com.conetdev.mydailydatabase.service.UsuarioService;
import com.conetdev.mydailydatabase.utils.PasswordUtils;
import com.conetdev.mydailydatabase.response.UsuarioResponse;

import org.springframework.stereotype.Service;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private UsuarioMapper usuarioMapper;

    @Autowired
    PasswordUtils passutil;

    private String class_name = UserController.class.getName();

    @Override
    public ResponseEntity<List<UsuarioResponse>> findAll() {

        try {
            List<Usuario> lista = usuarioRepository.findAll();
            List<UsuarioResponse> response = new ArrayList<>();

            for (Usuario usuario : lista) {
                response.add(usuarioMapper.toUsuarioResponse(usuario));
            }

            if (lista.isEmpty()) {
                Logger.getLogger(class_name).log(Level.INFO,
                        "HttpStatus: OK, List of users is empty");
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            Logger.getLogger(class_name).log(Level.INFO, "HtpStatus: OK, List of users returned");
            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (Exception e) {
            Logger.getLogger(class_name).log(Level.SEVERE,
                    "HttpStatus: INTERNAL_SERVER_ERROR, ", e.toString());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<UsuarioResponse> findById(Long id) {

        try {
            Optional<Usuario> usuario = usuarioRepository.findById(id);
            UsuarioResponse response = new UsuarioResponse();

            response = usuarioMapper.toUsuarioResponse(usuario.get());

            if (usuario.isEmpty()) {
                Logger.getLogger(class_name).log(Level.INFO,
                        "HttpStatus: OK, User not found");
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            Logger.getLogger(class_name).log(Level.INFO, "HtpStatus: OK, User information returned");
            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (Exception e) {
            Logger.getLogger(class_name).log(Level.SEVERE,
                    "HttpStatus: INTERNAL_SERVER_ERROR, ", e.toString());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<HttpStatus> save(UsuarioRequest dto) {
        try {
            Usuario usuario = new Usuario();

            usuarioMapper.toUsuarioEntity(dto, usuario);

            if (dto.getPassword() != null) {
                String pass = passutil.hash(dto.getPassword());
                usuario.setPasswordUsuario(pass);
            }

            usuarioRepository.save(usuario);

            Logger.getLogger(class_name).log(Level.INFO,
                    "HttpStatus: OK, User saved");

            return new ResponseEntity<>(HttpStatus.CREATED);

        } catch (Exception e) {
            Logger.getLogger(class_name).log(Level.SEVERE,
                    "HttpStatus: INTERNAL_SERVER_ERROR, ", e.toString());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<HttpStatus> updateById(Long id, UsuarioRequest dto) {

        try {
            Usuario usuario = usuarioRepository.findById(id).orElse(null);

            if (usuario == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            // Utilizamos el mapper para copiar todos los campos del DTO al entidad
            // existente
            usuarioMapper.toUsuarioEntity(dto, usuario);

            // Persistimos los cambios
            usuarioRepository.save(usuario);

            Logger.getLogger(class_name).log(Level.INFO,
                    "HttpStatus: OK, User updated");

            return new ResponseEntity<>(HttpStatus.OK);

        } catch (Exception e) {
            Logger.getLogger(class_name).log(Level.SEVERE,
                    "HttpStatus: INTERNAL_SERVER_ERROR, ", e.toString());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<HttpStatus> deleteById(Long id) {

        try {

            usuarioRepository.deleteById(id);

            Logger.getLogger(class_name).log(Level.INFO,
                    "HttpStatus: OK, User deleted");

            return new ResponseEntity<>(HttpStatus.OK);

        } catch (Exception e) {
            Logger.getLogger(class_name).log(Level.SEVERE,
                    "HttpStatus: INTERNAL_SERVER_ERROR, ", e.toString());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<HttpStatus> updatePassword(Long id, Map<String, String> request) {

        try {
            String newpass = request.get("newPassword");

            Usuario usuario = usuarioRepository.findById(id).orElse(null);
            if (usuario == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            usuario.setPasswordUsuario(passutil.hash(newpass));

            usuarioRepository.save(usuario);
            return new ResponseEntity<>(HttpStatus.OK);

        } catch (Exception e) {
            Logger.getLogger(class_name).log(Level.SEVERE, "Error on updatePassword(): ",
                    e.toString());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
