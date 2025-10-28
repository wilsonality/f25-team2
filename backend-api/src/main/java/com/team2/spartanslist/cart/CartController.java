package com.team2.spartanslist.cart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CartController {
    @Autowired
    private CartService cartService;

    @PostMapping("/{shopperID}/cart/{offerID}")
    public Cart addToCart(@PathVariable Long shopperID, @PathVariable Long offerID) {
        return cartService.addToCart(shopperID, offerID);
    }
}
