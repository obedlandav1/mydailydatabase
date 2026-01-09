package com.conetdev.mydailydatabase.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "tipocuenta")
public class TipoCuenta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre", nullable = false, length = 50)
    private String nombre; // Ej: Cuenta Corriente, Cuenta de Ahorros, Caja

    @Column(name = "estado", nullable = false)
    private Integer estado = 1; // 1=Activo, 2=Inactivo

}
