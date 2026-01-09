package com.conetdev.mydailydatabase.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.conetdev.mydailydatabase.model.Cuenta;
import com.conetdev.mydailydatabase.repository.CuentaRepository;
import com.conetdev.mydailydatabase.response.CuentaResponse;
import com.conetdev.mydailydatabase.service.CuentaService;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class CuentaServiceImpl implements CuentaService {

    @Autowired
    private CuentaRepository cuentaRepository;

    private String className = CuentaServiceImpl.class.getName();

    @Override
    public ResponseEntity<List<CuentaResponse>> listarCuentasActivas() {
        try {
            List<Cuenta> cuentas = cuentaRepository.findAllActivas();
            List<CuentaResponse> responses = new ArrayList<>();

            for (Cuenta c : cuentas) {
                CuentaResponse response = new CuentaResponse();
                response.setId(c.getId());
                response.setRazonSocialNombre(c.getRazonSocial().getNombreCorto());
                response.setBancoNombre(c.getBanco().getNombre());
                response.setTipoCuentaNombre(c.getTipoCuenta().getNombre());
                response.setTipoMonedaCodigo(c.getTipoMoneda().getCodigo());
                response.setNumeroCuenta(c.getNumeroCuenta());
                response.setNumeroInterbancario(c.getNumeroInterbancario());
                response.setEstado(c.getEstado());

                responses.add(response);
            }

            Logger.getLogger(className).log(Level.INFO, "Cuentas activas listadas: " + responses.size());
            return new ResponseEntity<>(responses, HttpStatus.OK);

        } catch (Exception e) {
            Logger.getLogger(className).log(Level.SEVERE, "Error al listar cuentas", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<List<CuentaResponse>> listarCuentasPorRazonSocial(Long razonSocialId) {
        try {
            List<Cuenta> cuentas = cuentaRepository.findByRazonSocialIdAndEstadoActivo(razonSocialId);
            List<CuentaResponse> responses = new ArrayList<>();

            for (Cuenta c : cuentas) {
                CuentaResponse response = new CuentaResponse();
                response.setId(c.getId());
                response.setRazonSocialNombre(c.getRazonSocial().getNombreCorto());
                response.setBancoNombre(c.getBanco().getNombre());
                response.setTipoCuentaNombre(c.getTipoCuenta().getNombre());
                response.setTipoMonedaCodigo(c.getTipoMoneda().getCodigo());
                response.setNumeroCuenta(c.getNumeroCuenta());
                response.setNumeroInterbancario(c.getNumeroInterbancario());
                response.setEstado(c.getEstado());

                responses.add(response);
            }

            Logger.getLogger(className).log(Level.INFO, "Cuentas para razón social: " + responses.size());
            return new ResponseEntity<>(responses, HttpStatus.OK);

        } catch (Exception e) {
            Logger.getLogger(className).log(Level.SEVERE, "Error al listar cuentas por razón social", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
