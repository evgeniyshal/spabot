package com.worldofmassage.spabot.controller;

import com.worldofmassage.spabot.entity.Offer;
import com.worldofmassage.spabot.entity.User;
import com.worldofmassage.spabot.service.RoleService;
import com.worldofmassage.spabot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@Controller
public class UserController {
  private final UserService userService;
  private final RoleService roleService;

  @Autowired
  public UserController(UserService userService, RoleService roleService) {
      this.userService = userService;
      this.roleService = roleService;
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

    @GetMapping("/user-update")
    public String updateUserForm(@RequestParam(value = "id") int id, Model model) {
        User user = userService.findById(id);
        model.addAttribute("user", user);
        return "user-update";
    }

    @PostMapping("/user-update")
    public String updateUser(@Valid User user,
                              @RequestParam(value = "authorities") String[] authorities,
                              BindingResult bindingResult,
                              Model model){

        if (bindingResult.hasErrors()) {
            return "user-update";
        }

        if (!user.getPassword().equals(user.getPasswordConfirm())){
            model.addAttribute("confirmError", "Пароли не совпадают");
            return "user-update";
        }

        userService.save(user, authorities);

        return "redirect:/profile";
    }
}
