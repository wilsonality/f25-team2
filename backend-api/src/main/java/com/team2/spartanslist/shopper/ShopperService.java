package com.team2.spartanslist.shopper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShopperService {
    @Autowired 
    private ShopperRepository shopperRepository;

    /*
     * Post
     */
    
     public Shopper createShopper(Shopper shopper) {
        return shopperRepository.save(shopper);
     }
}
