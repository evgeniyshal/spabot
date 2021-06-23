package com.worldofmassage.spabot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
public class LoginController {

    @RequestMapping("/")
    public String index(Principal principal){
        System.out.println(principal.getName());
        return "index";
    }
}
