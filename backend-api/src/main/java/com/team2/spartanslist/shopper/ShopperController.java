package com.team2.spartanslist.shopper;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/shoppers")
public class ShopperController {
    @Autowired
    private ShopperService shopperService;

    // Returns a list of all Shopper entities
    @GetMapping
    public List<Shopper> getAllShoppers() {
        return shopperService.getAllShoppers();
    }

    // Gets a Shopper entity by ID
    @GetMapping("/{shopperID}") 
    public Shopper getShopper(@PathVariable Long shopperID) {
        return shopperService.getShopper(shopperID);
    }

    // Insert a new Shopper entity into the table
    @PostMapping
    public Shopper createShopper(@RequestBody Shopper newShopper) {
    return shopperService.createShopper(newShopper);
    }

    // Update a Shopper entity
    @PutMapping("/{user_ID}")
    public Shopper updateShopper(@PathVariable Long shopperID, @RequestBody Shopper updatedShopper) {
    return shopperService.updateShopper(shopperID, updatedShopper);
    }

}
