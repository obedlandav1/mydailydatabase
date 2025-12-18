package com.conetdev.mydailydatabase.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "tipoidentidad")
public class TipoIdentidad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombrecodigo")
    private String nombreCodigo;

    @Column(name = "nombrecorto")
    private String nombreCorto;

    @Column(name = "nombrelargo")
    private String nombreLargo;
}
