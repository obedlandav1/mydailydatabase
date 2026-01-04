package com.conetdev.mydailydatabase.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmpresaRequest {
    private Long tipoIdentidadId;
    private String nombreCorto;
    private String nombreLargo;
    private String numIdentidad;
    private Integer estado;
}