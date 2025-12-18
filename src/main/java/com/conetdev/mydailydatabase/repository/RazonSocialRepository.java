package com.conetdev.mydailydatabase.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.conetdev.mydailydatabase.models.RazonSocial;

@Repository
public interface RazonSocialRepository extends JpaRepository<RazonSocial, Long> {
    // Ejemplo: Buscar por RUC/Num Identidad
    // Optional<RazonSocial> findByNumIdentidad(String numIdentidad);
}