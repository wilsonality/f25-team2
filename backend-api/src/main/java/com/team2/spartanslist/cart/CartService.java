package com.team2.spartanslist.cart;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.team2.spartanslist.Global;
import com.team2.spartanslist.offer.Offer;
import com.team2.spartanslist.offer.OfferRepository;
import com.team2.spartanslist.offer.OfferService;
import com.team2.spartanslist.shopper.Shopper;
import com.team2.spartanslist.shopper.ShopperService;

@Service
public class CartService {
    @Autowired
    private ShopperService shopperService;
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private OfferService offerService;

    /** method to get content of a shopper cart
     * 
     * note from wilson : i did change this, and apologies. but it's because
     * when you do the join column, the offer is an attribute of the cart object.
     * that's why i mentioned it.
     * 
     * @param shopperID
     * @return
     */

    public List<Cart> getCart(Shopper shopper) {
      // List<Long> offerIDs = cartRepository.findOfferIDsByShopperID(Global.shopperID);
      // List<Offer> offers = new ArrayList<>();

      // for (Long offerID : offerIDs) {
      //   Offer offer = offerService.getOfferById(offerID);
      //   if (offer == null){ continue;}
      //   offers.add(offer);
      // }

      // return offers;
      List<Cart> cart = cartRepository.findByShopper(shopper);
      if (cart == null) {return null;}

      return cart;

    }

    /** method to add a cart item
     * 
     * @param shopper
     * @param offer
     * @return
     */
    public Cart addToCart(Shopper shopper, Offer offer) {
      Cart cartItem = new Cart();
      cartItem.setShopper(shopper);
      cartItem.setOffer(offer);

      return cartRepository.save(cartItem);
    }

    /** method to get cart item
     * 
     * 
     */
    public Cart getCartItem(Shopper shopper, Offer offer) {
      return cartRepository.findByShopperAndOffer(shopper, offer);
    }
    

    /** method to delete a cart item
     * 
     * @param shopper owner of cart to delete
     * @param offer offer remove from cart
     * @return
     */
    public Object deleteFromCart(Shopper shopper, Offer offer) {
      Cart item = cartRepository.findByShopperAndOffer(shopper, offer);
      if (item == null) {return false;}
      cartRepository.deleteById(item.getCartItemID());
      return true;

      
    }
}
