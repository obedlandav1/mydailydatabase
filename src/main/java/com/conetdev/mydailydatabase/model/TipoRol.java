package com.conetdev.mydailydatabase.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "tiporol")
public class TipoRol {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombrecorto")
    private String nombreCorto;

    @Column(name = "nombrelargo")
    private String nombreLargo;

    // --- CORRECCIÓN DE RECURSIVIDAD ---
    @ManyToMany(mappedBy = "roles")
    @JsonIgnore // 1. Evita que Jackson serialice la lista de usuarios al pedir un Rol
    private List<Usuario> usuarios;
}