package com.team2.spartanslist;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.team2.spartanslist.offer.Offer;
import com.team2.spartanslist.offer.OfferService;
import com.team2.spartanslist.seller.SellerService;
import com.team2.spartanslist.shopper.ShopperService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class AppController {
    @Autowired
    private final OfferService offerService;
    @Autowired
    private final SellerService sellerService;
    @Autowired
    private final ShopperService shopperService;

     

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

    // Verification todo. Just matches username for now.
    @PostMapping("/login/seller")
    public Object verifySeller(String username, String password) {
        Long sellerID = sellerService.getSellerByUsername(username).getSellerID();
        Global.sellerID = sellerID;

        return "redirect:/sellers/home";
    }

    @PostMapping("/login/shopper")
    public Object verifyShopper(String username, String password) {
        Long shopperID = shopperService.getShopperByUsername(username).getShopperID();
        Global.shopperID = shopperID;

        return "redirect:/shoppers/myprofile";
    }
    
}
