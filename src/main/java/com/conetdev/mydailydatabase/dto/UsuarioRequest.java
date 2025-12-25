package com.conetdev.mydailydatabase.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Set;

/**
 * DTO usado para crear o actualizar un Usuario.
 * Los campos coinciden con el payload que envía el frontend.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioRequest {

    private String nombre;
    private String apellido;
    private String identidad;
    private String password;
    private String celular;
    private int estado;
    // Conjunto de IDs de roles (solo el id es necesario para la actualización)
    private Set<Long> roles;
    // ID del tipo de identidad
    private Long tipoIdentidad;
}