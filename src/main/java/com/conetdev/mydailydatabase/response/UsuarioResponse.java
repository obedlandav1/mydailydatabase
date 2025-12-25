package com.conetdev.mydailydatabase.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioResponse {
    /** Identificador del usuario. */
    private Long id;
    private String nombre;
    private String apellido;
    private String identidad;
    private String celular;
    private int estado;
    /** Roles del usuario (many‑to‑many). */
    private Set<RoleDto> roles;
    /** Tipo de identidad (many‑to‑one). */
    private TipoIdentidadDto tipoIdentidad;

    /*
     * -------------------------------------------------------------
     * Sub‑DTOs internos: RoleDto y TipoIdentidadDto
     * -------------------------------------------------------------
     */
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class RoleDto {
        private Long id;
        private String nombreLargo;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class TipoIdentidadDto {
        private Long id;
        private String nombreCorto;
    }
}