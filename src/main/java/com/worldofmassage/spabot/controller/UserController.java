package com.worldofmassage.spabot.controller;

import com.worldofmassage.spabot.entity.User;
import com.worldofmassage.spabot.service.RoleService;
import com.worldofmassage.spabot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@Controller
@RequestMapping(value = "/users")
public class UserController {
  private final UserService userService;
  private final RoleService roleService;

  @Autowired
  public UserController(UserService userService, RoleService roleService) {
      this.userService = userService;
      this.roleService = roleService;
    }

    @GetMapping(value = "/show")
    public String showData(Model model){
        model.addAttribute("users", userService.findAll());
        return "users";
    }

//    @GetMapping("/user-create")
//    public String createUserForm(Model model) {
//        model.addAttribute("roles", roleService.findAll());
//        model.addAttribute("user", new User());
//        return "user-create";
//    }
//
//    @PostMapping("/user-create")
//    public String createUser(User user,
//                             @RequestParam(value = "authorities") String[] authorities) {
//        userService.save(user, authorities);
//        return "redirect:/";
//    }

    @GetMapping("/user-update")
    public String updateUserForm(@RequestParam(value = "id") int id, Model model) {
        User user = userService.findById(id);
        model.addAttribute("user", user);
        return "profile-update";
    }

    @PostMapping("/user-update")
    public String updateUser(@Valid User user,
                              BindingResult bindingResult,
                              Model model,
                              @RequestParam(value = "authorities") String[] authorities){
        if (bindingResult.hasErrors()) {
            return "profile-update";
        }

        if (!user.getPassword().equals(user.getPasswordConfirm())){
            model.addAttribute("confirmError", "Пароли не совпадают");
            return "profile-update";
        }

        if (!userService.save(user, authorities)){
            model.addAttribute("usernameError", "Пользователь с данным логином уже существует");
            return "profile-update";
        }

        return "redirect:/";
    }
}
