package com.conetdev.mydailydatabase.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CuentaResponse {

    private Long id;
    private String razonSocialNombre;
    private String bancoNombre;
    private String tipoCuentaNombre;
    private String tipoMonedaCodigo;
    private String numeroCuenta;
    private String numeroInterbancario;
    private Integer estado;

}
