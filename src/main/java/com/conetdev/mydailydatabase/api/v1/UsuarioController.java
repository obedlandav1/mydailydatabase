package com.conetdev.mydailydatabase.api.v1;

import java.util.List;
import java.util.Map;

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

import com.conetdev.mydailydatabase.repository.UsuarioRepository;
import com.conetdev.mydailydatabase.request.UsuarioRequest;
import com.conetdev.mydailydatabase.response.UsuarioResponse;
import com.conetdev.mydailydatabase.service.UsuarioService;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api/v1")
public class UsuarioController {

    @Autowired
    UsuarioRepository userRepository;

    @Autowired
    UsuarioService usuarioService;

    @GetMapping("/usuario/cargar")
    public ResponseEntity<List<UsuarioResponse>> getAll(@RequestParam(required = false) Long id) {
        return usuarioService.findAll();
    }

    @GetMapping("/usuario/buscar/{id}")
    public ResponseEntity<UsuarioResponse> getById(@PathVariable("id") Long id) {
        return usuarioService.findById(id);
    }

    @PostMapping("/usuario/crear")
    public ResponseEntity<HttpStatus> create(@RequestBody UsuarioRequest entidad) {
        return usuarioService.save(entidad);
    }

    @PutMapping("/usuario/actualizar/{id}")
    public ResponseEntity<HttpStatus> update(@PathVariable("id") Long id, @RequestBody UsuarioRequest dto) {
        return usuarioService.updateById(id, dto);
    }

    @DeleteMapping("/usuario/eliminar/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") Long id) {
        return usuarioService.deleteById(id);
    }

    @PutMapping("/usuario/password/{id}")
    public ResponseEntity<HttpStatus> updatePassword(@PathVariable("id") Long id,
            @RequestBody Map<String, String> request) {

        return usuarioService.updatePassword(id, request);
    }

}
