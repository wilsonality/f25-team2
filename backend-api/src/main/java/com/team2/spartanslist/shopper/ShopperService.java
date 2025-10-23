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

     public Shopper getShopper(Long user_ID) {
         return shopperRepository.findById(user_ID).orElse(null);
     }

    /*
     * Post
     */
     public Shopper createShopper(Shopper newShopper) {
         return shopperRepository.save(newShopper);
     }

     /*
      * Put
      */
      public Shopper updateShopper(Long user_ID, Shopper updatedShopper) {
         shopperRepository.save(updatedShopper);
         return getShopper(user_ID);
      }
}
