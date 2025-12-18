package com.conetdev.mydailydatabase.models;

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
@Table(name = "razonsocial")
public class RazonSocial {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "tipoidentidad_id")
    private Long tipoIdentidadId; 

    @Column(name = "nombrecorto")
    private String nombreCorto;

    @Column(name = "nombrelargo")
    private String nombreLargo;

    @Column(name = "numidentidad")
    private String numIdentidad;

    private Integer estado;

    // --- CORRECCIÓN DE RECURSIVIDAD ---
    @ManyToMany(mappedBy = "razonesSociales")
    @JsonIgnore // Evita bucle JSON
    private List<Usuario> usuarios;
}