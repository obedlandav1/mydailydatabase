package com.conetdev.mydailydatabase.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AsistantController {
    
    @Autowired
    @RequestMapping("/asistant")
    public String asistant() {
        return "views/assistant";
    }

    @Autowired
    @RequestMapping("/asistant/cashbank")
    public String cashbank() {
        return "views/assistant-cashbank";
    }

    @Autowired
    @RequestMapping("/asistant/cashbank/saldos")
    public String saldos() {
        return "views/assistant-cashbank-saldos";
    }

    @Autowired
    @RequestMapping("/asistant/cashbank/movimientos")
    public String movimientos() {
        return "views/assistant-cashbank-movimientos";
    }

    @Autowired
    @RequestMapping("/asistant/cashbank/ingreso")
    public String ingreso() {
        return "views/assistant-cashbank-ingreso";
    }

    @Autowired
    @RequestMapping("/asistant/cashbank/retiro")
    public String retiro() {
        return "views/assistant-cashbank-retiro";
    }

}
