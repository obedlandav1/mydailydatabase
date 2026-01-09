package com.conetdev.mydailydatabase.service;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;
import com.conetdev.mydailydatabase.response.MovimientoResponse;

import java.util.List;
import java.util.Map;

public interface MovimientoService {

    ResponseEntity<List<MovimientoResponse>> cargar();

    ResponseEntity<Map<String, Object>> validarExcel(MultipartFile file);

    ResponseEntity<Map<String, Object>> guardarLote(List<Map<String, Object>> movimientos);

    ResponseEntity<?> descargarPlantilla();
}
