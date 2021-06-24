package com.worldofmassage.spabot.controller;

import com.worldofmassage.spabot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {
  private final UserService userService;

  @Autowired
  public UserController(UserService userService) {
      this.userService = userService;
    }

    @GetMapping(value = "/login")
    public String loginForm(){
        return "login";
    }
}
