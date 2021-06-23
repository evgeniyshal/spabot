package com.worldofmassage.spabot.controller;

import com.worldofmassage.spabot.entity.User;
import com.worldofmassage.spabot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UserController {
  private final UserService userService;

  @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registrationForm(User user){
        return "registration";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginForm(){
        return "login";
    }
}
