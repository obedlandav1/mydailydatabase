package com.conetdev.mydailydatabase.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.conetdev.mydailydatabase.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    // Para el Login
    Optional<Usuario> findByIdentidadUsuario(String identidadUsuario);

    // Para filtrar usuarios por ID de Razón Social (Many-to-Many)
    // JPA entiende que debe buscar dentro de la lista "razonesSociales"
    List<Usuario> findByRazonesSociales_Id(Long idRazonSocial);
}