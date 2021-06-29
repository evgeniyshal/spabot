package com.worldofmassage.spabot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String loginForm(Model model,
                            @RequestParam(value = "error", required = false) boolean error,
                            @RequestParam(value = "logout", required = false) boolean logout) {
        model.addAttribute("error", error);
        model.addAttribute("logout", logout);
        return "login";
    }
}
