package com.team2.spartanslist.cart;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.team2.spartanslist.offer.Offer;

@Controller
@RequestMapping("/carts")
public class CartController {
    @Autowired
    private CartService cartService;

    @PostMapping("/{shopperID}/cart/{offerID}")
    public Cart addToCart(@PathVariable Long shopperID, @PathVariable Long offerID) {
        return cartService.addToCart(shopperID, offerID);
    }

    @GetMapping("/{shopperID}/cart")
    public List<Offer> getCart(@PathVariable Long shopperID) {
        return cartService.getCart(shopperID);
    }
}
