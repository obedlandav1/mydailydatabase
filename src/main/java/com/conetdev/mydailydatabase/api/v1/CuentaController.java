package com.conetdev.mydailydatabase.api.v1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.conetdev.mydailydatabase.response.CuentaResponse;
import com.conetdev.mydailydatabase.service.CuentaService;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api/v1/cuenta")
public class CuentaController {

    @Autowired
    private CuentaService cuentaService;

    private String className = CuentaController.class.getName();

    @GetMapping("/listar")
    public ResponseEntity<List<CuentaResponse>> listarCuentasActivas() {
        Logger.getLogger(className).log(Level.INFO, "Listando cuentas activas");
        return cuentaService.listarCuentasActivas();
    }

    @GetMapping("/listar/{razonSocialId}")
    public ResponseEntity<List<CuentaResponse>> listarCuentasPorRazonSocial(
            @PathVariable Long razonSocialId) {
        Logger.getLogger(className).log(Level.INFO, "Listando cuentas para razón social: " + razonSocialId);
        return cuentaService.listarCuentasPorRazonSocial(razonSocialId);
    }

}
