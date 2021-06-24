package com.worldofmassage.spabot.controller;

import com.worldofmassage.spabot.entity.Offer;
import com.worldofmassage.spabot.repository.OfferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value = "/offers")
public class OfferController {

    private final OfferRepository offerRepository;

    @Autowired
    public OfferController(OfferRepository offerRepository) {
        this.offerRepository = offerRepository;
    }

    @GetMapping(value = "/show")
    public String showData(Model model){
        model.addAttribute("offers", offerRepository.findAll());
        return "offers";
    }

    @GetMapping("/update")
    public String updateOfferForm(@RequestParam(value = "id") int id, Model model) {
        Offer offer = offerRepository.findById(id);
        model.addAttribute("offer", offer);
        return "offer-update";
    }

    @PostMapping("/update")
    public String updateOffer(Offer offer) {
        offerRepository.save(offer);
        return "redirect:/offers/show";
    }
}
