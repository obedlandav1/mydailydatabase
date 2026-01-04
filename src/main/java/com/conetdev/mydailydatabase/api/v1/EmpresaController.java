package com.conetdev.mydailydatabase.api.v1;

import java.util.List;
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

import com.conetdev.mydailydatabase.request.AssignUsersRequest;
import com.conetdev.mydailydatabase.request.EmpresaRequest;
import com.conetdev.mydailydatabase.response.EmpresaResponse;
import com.conetdev.mydailydatabase.service.EmpresaService;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api/v1")
public class EmpresaController {

    @Autowired
    private EmpresaService empresaService;

    @GetMapping("/empresa/cargar")
    public ResponseEntity<List<EmpresaResponse>> getAll(@RequestParam(required = false) Long id) {
        // The service method does not use the id parameter, kept for signature
        // consistency with other controllers
        return empresaService.findAll();
    }

    @GetMapping("/empresa/buscar/{id}")
    public ResponseEntity<EmpresaResponse> getById(@PathVariable("id") Long id) {
        return empresaService.findById(id);
    }

    @PostMapping("/empresa/crear")
    public ResponseEntity<HttpStatus> create(@RequestBody EmpresaRequest request) {
        return empresaService.save(request);
    }

    @PutMapping("/empresa/actualizar/{id}")
    public ResponseEntity<HttpStatus> update(@PathVariable("id") Long id, @RequestBody EmpresaRequest request) {
        return empresaService.update(id, request);
    }

    @DeleteMapping("/empresa/eliminar/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") Long id) {
        return empresaService.deleteById(id);
    }

    @PutMapping("/empresa/asignar-usuarios/{id}")
    public ResponseEntity<HttpStatus> assignUsers(@PathVariable("id") Long id,
            @RequestBody AssignUsersRequest request) {
        return empresaService.assignUsers(id, request);
    }
}
