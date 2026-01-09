package com.conetdev.mydailydatabase.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "bancos")
public class Banco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

    @Column(name = "codigo", length = 50)
    private String codigo;

    @Column(name = "estado", nullable = false)
    private Integer estado = 1; // 1=Activo, 2=Inactivo

}
