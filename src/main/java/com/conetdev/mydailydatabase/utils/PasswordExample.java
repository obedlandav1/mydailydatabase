package com.conetdev.mydailydatabase.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PasswordExample {

    private final PasswordUtils passwordUtils;

    @Autowired
    public PasswordExample(PasswordUtils passwordUtils) {
        this.passwordUtils = passwordUtils;
    }

    /**
     * Encripta una contraseña utilizando PasswordUtils.
     * 
     * @param plainPassword La contraseña en texto plano.
     * @return La contraseña encriptada (hash).
     */
    public String encriptarClave(String plainPassword) {
        return passwordUtils.hash(plainPassword);
    }

    public void mostrarClaveEncriptada() {
        System.out.println("Clave encriptada: " + encriptarClave("46410175"));
    }

    /**
     * Verifica si una contraseña coincide con un hash almacenado.
     * 
     * @param plainPassword  La contraseña en texto plano.
     * @param hashedPassword El hash almacenado.
     * @return true si coinciden, false de lo contrario.
     */
    public boolean verificarClave(String plainPassword, String hashedPassword) {
        return passwordUtils.matches(plainPassword, hashedPassword);
    }

    public static void main(String[] args) {
        PasswordUtils utils = new PasswordUtils();
        PasswordExample example = new PasswordExample(utils);
        example.mostrarClaveEncriptada();
    }
}
