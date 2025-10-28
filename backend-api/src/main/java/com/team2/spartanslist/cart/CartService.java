package com.team2.spartanslist.cart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.team2.spartanslist.shopper.Shopper;
import com.team2.spartanslist.shopper.ShopperRepository;

@Service
public class CartService {
    @Autowired
    private ShopperRepository shopperRepository;

    @Autowired
    private CartRepository cartRepository;

    private Shopper shopper;
    private Cart shopperCart;

    public Cart addToCart(Long shopperID, Long offerID) {
        shopper = shopperRepository.findById(shopperID).orElse(null);
        shopperCart = shopper.getCart();
        shopperCart.offers.add(offerID);
        cartRepository.save(shopperCart);
        return shopperCart;
    }
}
