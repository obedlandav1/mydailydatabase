package com.conetdev.mydailydatabase.service;

import org.springframework.http.ResponseEntity;
import com.conetdev.mydailydatabase.response.CuentaResponse;

import java.util.List;

public interface CuentaService {

    ResponseEntity<List<CuentaResponse>> listarCuentasActivas();

    ResponseEntity<List<CuentaResponse>> listarCuentasPorRazonSocial(Long razonSocialId);

}
