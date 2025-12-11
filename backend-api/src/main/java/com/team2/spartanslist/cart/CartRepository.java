package com.team2.spartanslist.cart;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.team2.spartanslist.shopper.Shopper;
import com.team2.spartanslist.offer.Offer;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long>{
    @Query(value = "select offerID from cart where shopperID = %?1", nativeQuery = true)  
    public List<Long> findOfferIDsByShopperID(Long shopperID);

    @Query(value = "DELETE FROM cart WHERE cart_itemID IN (SELECT cart_itemID FROM cart WHERE shopperID = ?1 AND offerID = ?2 LIMIT 1) RETURNING *", nativeQuery = true)
    public Object deleteFromCart(Long shopperID, Long offerID);

    @Query(value = "select * from cart where shopperID = ?1", nativeQuery = true)
    public List<Cart> findByShopperID(Long shopperID);

    public List<Cart> findByShopper(Shopper shopper);

    public Cart findByShopperAndOffer(Shopper shopper, Offer offer);
    public Cart deleteByShopperAndOffer(Shopper shopper, Offer offer);
}
