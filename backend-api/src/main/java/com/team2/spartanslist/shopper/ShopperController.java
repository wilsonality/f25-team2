package com.team2.spartanslist.shopper;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ShopperController {
    @Autowired
    private ShopperService shopperService;

    /*
     * Get Mappings
     */
    @GetMapping("/shopper")
    public List<Shopper> getAllShoppers() {
        return shopperService.getAllShoppers();
    }

    @GetMapping("/shopper/{user_ID}") 
    public Shopper getShopper(@PathVariable Long user_ID) {
        return shopperService.getShopper(user_ID);
    }

    /*
     * Post Mappings
     */
     @PostMapping("/shopper")
     public Shopper createShopper(@RequestBody Shopper newShopper) {
        return shopperService.createShopper(newShopper);
     }

     /*
      * Put Mappings
      */
      @PutMapping("/shopper/update/{user_ID}")
      public Shopper updateShopper(@PathVariable Long user_ID, @RequestBody Shopper updatedShopper) {
        return shopperService.updateShopper(user_ID, updatedShopper);
      }

}
