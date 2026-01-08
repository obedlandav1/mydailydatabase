package com.conetdev.mydailydatabase.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CuentaRequest {

    private Long razonSocialId;
    private Long bancoId;
    private Long tipoCuentaId;
    private Long tipoMonedaId;
    private String numeroCuenta;
    private String numeroInterbancario;
    private Integer estado = 1;

}
