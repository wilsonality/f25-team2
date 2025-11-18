package com.team2.spartanslist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.team2.spartanslist.offer.OfferService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class AppController {
    @Autowired
    private final OfferService offerService;

    @GetMapping({"", "/", "/home"})
    public String redirectToLanding(Model model) {
        model.addAttribute("title", "Welcome to Spartan's List");
        return "guest-home";
    }

    @GetMapping("/login")
    public Object showLogInPage(Model model){
        model.addAttribute("title", "Sign In to Spartan's List");
        model.addAttribute("offers", offerService.getAllOffers());
        return "log-in";
    }
    
}
