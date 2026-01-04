package com.conetdev.mydailydatabase.service;

import java.util.List;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.conetdev.mydailydatabase.request.UsuarioRequest;
import com.conetdev.mydailydatabase.response.UsuarioResponse;

public interface UsuarioService {

    public ResponseEntity<List<UsuarioResponse>> findAll();

    public ResponseEntity<UsuarioResponse> findById(Long id);

    public ResponseEntity<HttpStatus> save(UsuarioRequest entidad);

    public ResponseEntity<HttpStatus> updateById(Long id, UsuarioRequest entidad);

    public ResponseEntity<HttpStatus> deleteById(Long id);

    public ResponseEntity<HttpStatus> updatePassword(Long id, Map<String, String> request);

}
