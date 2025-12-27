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
public class EmpresaRequest {

    private Long id;
    private String nombrecomercial;
    private String razonsocial;
    private String identidad;
    private int estado;
    private Set<UserDto> users;
    private TipoIdentidadDto tipoIdentidad;

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UserDto {
        private Long id;
        private String nombre;
        private String apellido;
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