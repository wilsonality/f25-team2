package com.team2.spartanslist.shopper;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShopperService {
    @Autowired 
    private ShopperRepository shopperRepository;

    /*
     * Get
     */

     public List<Shopper> getAllShoppers() {
        return shopperRepository.findAll();
     }

    /*
     * Post
     */
    
     public Shopper createShopper(Shopper shopper) {
        return shopperRepository.save(shopper);
     }
}
