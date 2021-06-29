package com.worldofmassage.spabot.controller;

import com.worldofmassage.spabot.entity.Offer;
import com.worldofmassage.spabot.repository.OfferRepository;
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

    @GetMapping("/create")
    public String createOfferForm(Model model) {
        model.addAttribute("offer", new Offer());
        return "offer-create";
    }

    @PostMapping("/create")
    public String createOffer(@Valid Offer offer,
                              BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "offer-create";
        }
        offerRepository.save(offer);
        return "redirect:/offers/show";
    }

    @GetMapping("/update")
    public String updateOfferForm(@RequestParam(value = "id") int id, Model model) {
        Offer offer = offerRepository.findById(id);
        model.addAttribute("offer", offer);
        return "offer-update";
    }

    @PostMapping("/update")
    public String updateOffer(@Valid Offer offer,
                              BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "offer-update";
        }
        offerRepository.save(offer);
        return "redirect:/offers/show";
    }

    @GetMapping("/delete")
    public String deleteOffer(@RequestParam(value = "id") int id) {
        offerRepository.deleteById(id);
        return "redirect:/offers/show";
    }
}
