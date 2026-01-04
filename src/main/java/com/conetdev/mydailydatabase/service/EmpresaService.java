package com.conetdev.mydailydatabase.service;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.conetdev.mydailydatabase.request.AssignUsersRequest;
import com.conetdev.mydailydatabase.request.EmpresaRequest;
import com.conetdev.mydailydatabase.response.EmpresaResponse;

public interface EmpresaService {
    ResponseEntity<List<EmpresaResponse>> findAll();

    ResponseEntity<EmpresaResponse> findById(Long id);

    ResponseEntity<HttpStatus> save(EmpresaRequest request);

    ResponseEntity<HttpStatus> update(Long id, EmpresaRequest request);

    ResponseEntity<HttpStatus> deleteById(Long id);

    ResponseEntity<HttpStatus> assignUsers(Long id, AssignUsersRequest request);
}
