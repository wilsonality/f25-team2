package com.team2.spartanslist.cart;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.team2.spartanslist.Global;
import com.team2.spartanslist.offer.Offer;

@Controller
public class CartController {
    @Autowired
    private CartService cartService;

    @GetMapping("/getCart")
    public List<Offer> getCart() {
        return cartService.getCart();
    }

    @PostMapping("/addToCart/{offerID}")
    public String addToCart(@PathVariable Long offerID) {
        cartService.addToCart(Global.shopperID, offerID);
        return "redirect:/shoppers/cart";
    }

    @PostMapping("/cart/delete/{offerID}")
    public String deleteFromCart(@PathVariable Long offerID) {
        cartService.deleteFromCart(offerID);
        
        return "redirect:/shoppers/cart";
    }
}
