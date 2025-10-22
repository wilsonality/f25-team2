package com.team2.spartanslist.shopper;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

    /*
     * Post Mappings
     */

     @PostMapping("/shopper")
     public Shopper createShopper(@RequestBody Shopper shopper) {
        return shopperService.createShopper(shopper);
     }
}
