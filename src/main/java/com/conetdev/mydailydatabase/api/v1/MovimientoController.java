package com.conetdev.mydailydatabase.api.v1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.conetdev.mydailydatabase.response.MovimientoResponse;
import com.conetdev.mydailydatabase.service.MovimientoService;

import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api/v1/movimiento")
public class MovimientoController {

    @Autowired
    private MovimientoService movimientoService;

    private String className = MovimientoController.class.getName();

    @GetMapping("/cargar")
    public ResponseEntity<List<MovimientoResponse>> cargarMovimientos() {
        Logger.getLogger(className).log(Level.INFO, "Cargando movimientos");
        return movimientoService.cargar();
    }

    @PostMapping("/validar-excel")
    public ResponseEntity<Map<String, Object>> validarExcel(
            @RequestParam("file") MultipartFile file) {
        Logger.getLogger(className).log(Level.INFO, "Validando archivo Excel");
        return movimientoService.validarExcel(file);
    }

    @PostMapping("/guardar-lote")
    public ResponseEntity<Map<String, Object>> guardarLote(
            @RequestBody List<Map<String, Object>> movimientos) {
        Logger.getLogger(className).log(Level.INFO, "Guardando lote de movimientos: " + movimientos.size());
        return movimientoService.guardarLote(movimientos);
    }

    @GetMapping("/descargar-plantilla")
    public ResponseEntity<?> descargarPlantilla() {
        Logger.getLogger(className).log(Level.INFO, "Descargando plantilla");
        return movimientoService.descargarPlantilla();
    }
}
