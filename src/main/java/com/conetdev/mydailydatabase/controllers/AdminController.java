package com.conetdev.mydailydatabase.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AdminController {
    @Autowired
    @RequestMapping("/administrator")
    public String administrator() {
        return "views/administrator";
    }

    @Autowired
    @RequestMapping("/administrator/users")
    public String users() {
        return "views/users";
    }

    @Autowired
    @RequestMapping("/administrator/companies")
    public String companies() {
        return "views/companies";
    }

}
