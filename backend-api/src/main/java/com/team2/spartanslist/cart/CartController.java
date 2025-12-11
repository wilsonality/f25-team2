package com.team2.spartanslist.cart;

import java.net.Authenticator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.team2.spartanslist.Global;
import com.team2.spartanslist.offer.Offer;
import com.team2.spartanslist.offer.OfferService;
import com.team2.spartanslist.shopper.Shopper;
import com.team2.spartanslist.shopper.ShopperService;

import ch.qos.logback.core.model.Model;

@Controller
public class CartController {
    @Autowired
    private CartService cartService;
    @Autowired
    private ShopperService shopperService;
    @Autowired
    private OfferService offerService;

    @PostMapping("/addToCart/{offerID}")
    public String addToCart(@PathVariable Long offerID, Authentication auth) {

        // if user not signed in
        if (auth == null || !auth.isAuthenticated()){
            return null;
        }
        // if offer does not exist
        Offer offer = offerService.getOfferById(offerID);
        if (offer == null) {
            return null;
        }

        Shopper user = shopperService.getShopperByPhone(auth.getName());
        cartService.addToCart(user, offer);
        return "redirect:/shoppers/cart";
    }

    @PostMapping("/cart/delete/{offerID}")
    public String deleteFromCart(@PathVariable Long offerID, Authentication auth) {

        // if user not signed in
        if (auth == null || !auth.isAuthenticated()){
            return null;
        }
        // if offer does not exist
        Offer offer = offerService.getOfferById(offerID);
        if (offer == null) {
            return null;
        }

        Shopper user = shopperService.getShopperByPhone(auth.getName());

        cartService.deleteFromCart(user, offer);
        
        return "redirect:/shoppers/cart";
    }
}
