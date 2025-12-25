package com.conetdev.mydailydatabase.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Set;

/**
 * DTO used for creating or updating a Usuario.
 * Contains the same fields as the entity but without JPA annotations.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioList {

    private Long id;
    private String nombre;
    private String apellido;
    private String identidad;
    private String celular;
    private int estado;
    private Set<RoleDto> roles;
    private TipoIdentidadDto tipoIdentidad;

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