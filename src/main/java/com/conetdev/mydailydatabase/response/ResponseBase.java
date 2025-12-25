package com.conetdev.mydailydatabase.response;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Clase base genérica que envuelve la respuesta de la API.
 *
 * @param <T> Tipo del cuerpo (payload) que se devuelve al cliente.
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseBase<T> {

    /** Código HTTP de la respuesta (por ejemplo 200, 404, 500). */
    private HttpStatus status;

    /** Mensaje descriptivo para el cliente. */
    private String message;

    /** Datos devueltos por la operación. */
    private T data;

}
