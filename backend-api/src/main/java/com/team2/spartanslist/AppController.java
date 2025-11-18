package com.team2.spartanslist;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.team2.spartanslist.offer.Offer;
import com.team2.spartanslist.offer.OfferService;
import com.team2.spartanslist.seller.Seller;
import com.team2.spartanslist.seller.SellerService;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import com.team2.spartanslist.Global;

@Controller
@RequiredArgsConstructor
public class AppController {
    @Autowired
    private final OfferService offerService;
    @Autowired
    private final SellerService sellerService;

     

    @GetMapping({"", "/", "/home"})
    public String redirectToLanding(Model model) {
        model.addAttribute("title", "Welcome to Spartan's List");
        List<Offer> offers = offerService.findByAvailability(true);
        model.addAttribute("offers", offers);
        return "guest-home";
    }

    @GetMapping("/login")
    public Object showLogInPage(Model model){
       String username = new String();
       String password = new String();

        model.addAttribute("title", "Sign In to Spartan's List");
        model.addAttribute("username", username);
        model.addAttribute("password", password);
        return "log-in";
    }

    @PostMapping("/login/seller")
    public Object verifySeller(String username, String password) {
        Long sellerID = sellerService.getSellerByUsername(username).getSellerID();
        Global.sellerID = sellerID;

        return "redirect:/sellers/" + Global.sellerID;
    }

    @PostMapping("/login/shopper")
    public Object verifyShopper(Model model){
        return "redirect:/shoppers/home";
    }
}
