package com.team2.spartanslist.cart;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.team2.spartanslist.Global;
import com.team2.spartanslist.offer.Offer;

@RestController
public class CartController {
    @Autowired
    private CartService cartService;

    @GetMapping("/getCart")
    public List<Offer> getCart() {
        return cartService.getCart();
    }

    @PostMapping("/addToCart/{offerID}")
    public Cart addToCart(@PathVariable Long offerID) {
        return cartService.addToCart(Global.shopperID, offerID);
    }
}
