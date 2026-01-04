package com.conetdev.mydailydatabase.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmpresaResponse {
    private Long id;
    private String nombreCorto;
    private String nombreLargo;
    private String numIdentidad;
    private Integer estado;
    private TipoIdentidadDto tipoIdentidad;

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class TipoIdentidadDto {
        private Long id;
        private String nombreCorto;
    }
}
