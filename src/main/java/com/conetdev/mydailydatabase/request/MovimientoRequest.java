package com.conetdev.mydailydatabase.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MovimientoRequest {

    private Long cuentasId;
    private Long tipoOperacionId;
    private LocalDate fechaEmision;
    private LocalDate fechaOperacion;
    private String periodoOperacion;
    private String numeroOperacion;
    private String descripcionOperacion;
    private String beneficiarioOperacion;
    private String glosaOperacion;
    private BigDecimal montoOperacion1;
    private BigDecimal tipoCambio;
    private BigDecimal montoOperacion2;
    private Integer estado;
}
