package com.conetdev.mydailydatabase.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "cuentas")
public class Cuenta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "razonsocial_id", nullable = false)
    private RazonesSociales razonSocial;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "bancos_id", nullable = false)
    private Banco banco;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "tipoCuenta_id", nullable = false)
    private TipoCuenta tipoCuenta;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "tipoMoneda_id", nullable = false)
    private TipoMoneda tipoMoneda;

    @Column(name = "numeroCuenta", nullable = false, length = 50)
    private String numeroCuenta;

    @Column(name = "numeroInterbancario", length = 50)
    private String numeroInterbancario;

    @Column(name = "estado", nullable = false)
    private Integer estado = 1; // 1=Activo, 2=Suspendido, 3=Inactivo

}
