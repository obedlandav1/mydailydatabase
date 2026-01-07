package com.conetdev.mydailydatabase.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;
import java.math.BigDecimal;

@Data
@Entity
@Table(name = "movimientos")
public class Movimiento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "cuentas_id", nullable = false)
    private Long cuentasId;

    @Column(name = "tipoOperacion_id", nullable = false)
    private Long tipoOperacionId;

    @Column(name = "fechaEmision", nullable = false)
    private LocalDate fechaEmision;

    @Column(name = "fechaOperacion", nullable = false)
    private LocalDate fechaOperacion;

    @Column(name = "periodoOperacion", length = 50)
    private String periodoOperacion;

    @Column(name = "numeroOperacion", length = 50)
    private String numeroOperacion;

    @Column(name = "descripcionOperacion", length = 300)
    private String descripcionOperacion;

    @Column(name = "beneficiarioOperacion", length = 300)
    private String beneficiarioOperacion;

    @Column(name = "glosaOperacion", length = 300)
    private String glosaOperacion;

    @Column(name = "montoOperacion1", precision = 20, scale = 2)
    private BigDecimal montoOperacion1;

    @Column(name = "tipoCambio", precision = 20, scale = 2)
    private BigDecimal tipoCambio;

    @Column(name = "montoOperacion2", precision = 20, scale = 2)
    private BigDecimal montoOperacion2;

    @Column(name = "estado", nullable = false)
    private Integer estado = 1; // 1=Activo, 2=Suspendido, 3=Eliminado
}
