package com.team2.spartanslist.cart;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.team2.spartanslist.offer.Offer;
import com.team2.spartanslist.offer.OfferRepository;
import com.team2.spartanslist.shopper.Shopper;
import com.team2.spartanslist.shopper.ShopperRepository;

@Service
public class CartService {
    @Autowired
    private ShopperRepository shopperRepository;
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private OfferRepository offerRepository;

    public Cart addToCart(Long shopperID, Long offerID) {
        Shopper shopper = shopperRepository.findById(shopperID).orElse(null);
        Cart shopperCart = shopper.getCart();

        shopperCart.offerIDList.add(offerID);
        cartRepository.save(shopperCart);

        return shopperCart;
    }

    public List<Offer> getCart(Long shopperID) {
        Shopper shopper = shopperRepository.findById(shopperID).orElse(null);
        Cart shopperCart = shopper.getCart();

        List<Offer> offerList = new ArrayList<>();
        Offer offer;

        for (Long offerID : shopperCart.offerIDList) {
            offer = offerRepository.findById(offerID).orElse(null);
            offerList.add(offer);
        }

        return offerList;
    }
}
