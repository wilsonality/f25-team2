package com.team2.spartanslist.cart;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long>{
    @Query(value = "select offerID from cart where shopperID = %?1", nativeQuery = true)  
    public List<Long> findOfferIDsByShopperID(Long shopperID);
}
