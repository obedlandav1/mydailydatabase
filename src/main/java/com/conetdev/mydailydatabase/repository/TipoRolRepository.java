package com.conetdev.mydailydatabase.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.conetdev.mydailydatabase.models.TipoRol;

@Repository
public interface TipoRolRepository extends JpaRepository<TipoRol, Long> {
    // Ejemplo: Buscar por nombre corto (ej: "ADMIN")
    // TipoRol findByNombreCorto(String nombreCorto);
}