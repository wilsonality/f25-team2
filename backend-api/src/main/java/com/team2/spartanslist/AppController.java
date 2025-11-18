package com.team2.spartanslist;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.team2.spartanslist.offer.Offer;
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
        List<Offer> offers = offerService.findByAvailability(true);
        model.addAttribute("offers", offers);
        return "guest-home";
    }

    @GetMapping("/login")
    public Object showLogInPage(Model model){
        model.addAttribute("title", "Sign In to Spartan's List");
        return "log-in";
    }

    @PostMapping("/login/seller")
    public Object verifySeller(Model model){
        // TODO security config/login implementation
        return "redirect:/sellers/home";
    }

    @PostMapping("/login/shopper")
    public Object verifyShopper(Model model){
        return "redirect:/shopper/home";
    }
    
}
