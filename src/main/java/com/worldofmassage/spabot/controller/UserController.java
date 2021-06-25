package com.worldofmassage.spabot.controller;

import com.worldofmassage.spabot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {
  private final UserService userService;

  @Autowired
  public UserController(UserService userService) {
      this.userService = userService;
    }

    @GetMapping("/login")
    public String loginForm(Model model,
                            @RequestParam(value = "error", required = false) boolean error,
                            @RequestParam(value = "logout", required = false) boolean logout) {
        model.addAttribute("error", error);
        model.addAttribute("logout", logout);
        return "login";
    }

    @GetMapping("/profile")
    public String profile(Model model) {
      model.addAttribute("user", userService.getCurrentUser());
      return "profile";
    }
}
