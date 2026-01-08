package com.conetdev.mydailydatabase.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "tipomoneda")
public class TipoMoneda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "codigo", nullable = false, length = 3)
    private String codigo; // USD, PEN, EUR, etc.

    @Column(name = "nombre", nullable = false, length = 50)
    private String nombre; // Dólar Americano, Sol Peruano, Euro, etc.

    @Column(name = "simbolo", length = 5)
    private String simbolo; // $, S/., €, etc.

    @Column(name = "estado", nullable = false)
    private Integer estado = 1; // 1=Activo, 2=Inactivo

}
