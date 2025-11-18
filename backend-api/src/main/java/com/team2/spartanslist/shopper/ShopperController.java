package com.team2.spartanslist.shopper;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/shoppers")
public class ShopperController {
    @Autowired
    private ShopperService shopperService;

    // Show forms endpoints
        /**
         * Endpoint to show the registration form
         */
        @GetMapping("/register")
        public String showRegisterForm(Model model) {
            Shopper newShopper = new Shopper();
            model.addAttribute(newShopper);

            return "/shopper/shopper-registration-form";
        } 

    // Get endpoints
        /** 
         * Endpoint to get all shoppers
         * 
         * @return a list of all shoppers
         */
        @GetMapping
        public List<Shopper> getAllShoppers() {
            return shopperService.getAllShoppers();
        }

        /**
         * Endpoint to get shopper by ID
         * 
         * @param shopperID
         * @return the shopper
         */
        @GetMapping("/{shopperID}/profile") 
        public String getShopper(Model model, @PathVariable Long shopperID) {
            Shopper shopper = shopperService.getShopper(shopperID);
            model.addAttribute("shopper", shopper);

            return "/shopper/shopper-profile";
        }


    // Add endpoints
        /**
         * Endpoint to add a shopper into the table
         * 
         * @param the shopper from the form information
         */
        @PostMapping
        public String createShopper(Shopper newShopper) {
            shopperService.createShopper(newShopper);
            Long shopperID = newShopper.getShopperID();

            return "redirect:/api/shoppers/" + shopperID + "/profile";
        }

    // Update endpoints
        /**
         * Endpoint to update a shopper
         * 
         * @param shopperID
         * @param updatedShopper
         * @return the page to the updated shopper
         */
        @PutMapping("/update/{user_ID}")
        public Shopper updateShopper(@PathVariable Long shopperID, @RequestBody Shopper updatedShopper) {
            return shopperService.updateShopper(shopperID, updatedShopper);
        }
}
