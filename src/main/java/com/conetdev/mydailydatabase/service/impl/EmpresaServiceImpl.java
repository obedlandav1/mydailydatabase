package com.conetdev.mydailydatabase.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.conetdev.mydailydatabase.mapper.EmpresaMapper;
import com.conetdev.mydailydatabase.model.RazonesSociales;
import com.conetdev.mydailydatabase.model.Usuarios;
import com.conetdev.mydailydatabase.repository.RazonSocialRepository;
import com.conetdev.mydailydatabase.repository.UsuarioRepository;
import com.conetdev.mydailydatabase.request.AssignUsersRequest;
import com.conetdev.mydailydatabase.request.EmpresaRequest;
import com.conetdev.mydailydatabase.response.EmpresaResponse;
import com.conetdev.mydailydatabase.service.EmpresaService;

@Service
public class EmpresaServiceImpl implements EmpresaService {

    @Autowired
    private RazonSocialRepository razonSocialRepository;

    @Autowired
    private EmpresaMapper empresaMapper;

    @Autowired
    private UsuarioRepository usuarioRepository;

    private final String className = EmpresaServiceImpl.class.getName();

    @Override
    public ResponseEntity<List<EmpresaResponse>> findAll() {
        try {
            List<RazonesSociales> lista = razonSocialRepository.findAll();
            List<EmpresaResponse> response = new ArrayList<>();

            for (RazonesSociales empresa : lista) {
                response.add(empresaMapper.toEmpresaResponse(empresa));
            }

            if (lista.isEmpty()) {
                Logger.getLogger(className).log(Level.INFO, "HttpStatus: NO_CONTENT, List of empresas is empty");
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            Logger.getLogger(className).log(Level.INFO, "HttpStatus: OK, List of empresas returned");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            Logger.getLogger(className).log(Level.SEVERE, "HttpStatus: INTERNAL_SERVER_ERROR, " + e.toString());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<EmpresaResponse> findById(Long id) {
        try {
            RazonesSociales entity = razonSocialRepository.findById(id).orElse(null);
            if (entity == null) {
                Logger.getLogger(className).log(Level.INFO, "HttpStatus: NO_CONTENT, Empresa not found");
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            EmpresaResponse response = empresaMapper.toEmpresaResponse(entity);
            Logger.getLogger(className).log(Level.INFO, "HttpStatus: OK, Empresa returned");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            Logger.getLogger(className).log(Level.SEVERE, "HttpStatus: INTERNAL_SERVER_ERROR, " + e.toString());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<HttpStatus> save(EmpresaRequest request) {
        try {
            RazonesSociales entity = empresaMapper.toEmpresaEntity(request);
            razonSocialRepository.save(entity);
            Logger.getLogger(className).log(Level.INFO, "HttpStatus: CREATED, Empresa saved");
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            Logger.getLogger(className).log(Level.SEVERE, "HttpStatus: INTERNAL_SERVER_ERROR, " + e.toString());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<HttpStatus> update(Long id, EmpresaRequest request) {
        try {
            RazonesSociales entity = razonSocialRepository.findById(id).orElse(null);
            if (entity == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            empresaMapper.toEmpresaEntity(request, entity);
            razonSocialRepository.save(entity);
            Logger.getLogger(className).log(Level.INFO, "HttpStatus: OK, Empresa updated");
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            Logger.getLogger(className).log(Level.SEVERE, "HttpStatus: INTERNAL_SERVER_ERROR, " + e.toString());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<HttpStatus> deleteById(Long id) {
        try {
            razonSocialRepository.deleteById(id);
            Logger.getLogger(className).log(Level.INFO, "HttpStatus: OK, Empresa deleted");
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            Logger.getLogger(className).log(Level.SEVERE, "HttpStatus: INTERNAL_SERVER_ERROR, " + e.toString());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<HttpStatus> assignUsers(Long id, AssignUsersRequest request) {
        try {
            RazonesSociales empresa = razonSocialRepository.findById(id).orElse(null);
            if (empresa == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            List<Usuarios> usuarios = usuarioRepository.findAllById(request.getUserIds());
            empresa.setUsuarios(usuarios);
            razonSocialRepository.save(empresa);
            Logger.getLogger(className).log(Level.INFO, "HttpStatus: OK, Users assigned to Empresa");
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            Logger.getLogger(className).log(Level.SEVERE, "HttpStatus: INTERNAL_SERVER_ERROR, " + e.toString());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
