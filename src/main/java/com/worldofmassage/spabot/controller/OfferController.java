package com.worldofmassage.spabot.controller;

import com.worldofmassage.spabot.repository.OfferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/offers")
public class OfferController {

    private OfferRepository offerRepository;

    @Autowired
    public OfferController(OfferRepository offerRepository) {
        this.offerRepository = offerRepository;
    }

    @GetMapping(value = "/show")
    public String showData(Model model){
        model.addAttribute("offers", offerRepository.findAll());
        return "offers";
    }
}
