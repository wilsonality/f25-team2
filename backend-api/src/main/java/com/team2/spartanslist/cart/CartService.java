package com.team2.spartanslist.cart;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.team2.spartanslist.Global;
import com.team2.spartanslist.offer.Offer;
import com.team2.spartanslist.offer.OfferRepository;
import com.team2.spartanslist.shopper.Shopper;
import com.team2.spartanslist.shopper.ShopperService;

@Service
public class CartService {
    @Autowired
    private ShopperService shopperService;
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private OfferRepository offerRepository;

    public Cart addToCart(Long shopperID, Long offerID) {
      Cart cartItem = new Cart();
      cartItem.setShopperID(shopperID);
      cartItem.setOfferID(offerID);

      return cartRepository.save(cartItem);
    }

    public List<Offer> getCart() {
      List<Long> offerIDs = cartRepository.findOfferIDsByShopperID(Global.shopperID);
      List<Offer> offers = new ArrayList<>();

      for (Long offerID : offerIDs) {
        Offer offer = offerRepository.findById(offerID).orElse(null);
        offers.add(offer);
      }

      return offers;
    }
}
