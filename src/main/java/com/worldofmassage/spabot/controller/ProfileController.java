package com.worldofmassage.spabot.controller;

import com.worldofmassage.spabot.entity.User;
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
@RequestMapping(value = "/profile")
public class ProfileController {
    private final UserService userService;

    @Autowired
    public ProfileController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/show")
    public String profile(Model model) {
        model.addAttribute("user", userService.getCurrentUser());
        return "profile";
    }

    @GetMapping("/update")
    public String updateProfileForm(@RequestParam(value = "id") int id, Model model) {
        User user = userService.findById(id);
        model.addAttribute("user", user);
        return "profile-update";
    }

    @PostMapping("/update")
    public String updateProfile(@Valid User user,
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

        return "redirect:/profile/show";
    }
}
