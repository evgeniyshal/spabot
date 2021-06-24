package com.worldofmassage.spabot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/offers")
public class OfferController {

    @GetMapping(value = "/show")
    public String showData(Model model){
//        model.addAttribute("offers", offerRepository.findByOrderByIdAsc());
        return "offers";
    }
}
