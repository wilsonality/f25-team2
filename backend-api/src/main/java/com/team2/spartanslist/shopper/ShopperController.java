package com.team2.spartanslist.shopper;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.team2.spartanslist.Global;

@Controller
@RequestMapping("/shoppers")
public class ShopperController {
    @Autowired
    private ShopperService shopperService;

    // Show forms endpoints
        /**
         * Show registration form
         * 
         * @return shopper registration view
         */
        @GetMapping("/register")
        public String showRegisterForm(Model model) {
            Shopper newShopper = new Shopper();
            model.addAttribute(newShopper);

            return "/shopper/shopper-registration-form";
        } 

        @GetMapping("/updateForm")
        public String showUpdateForm(Model model) {
            Shopper shopperToUpdate = shopperService.getShopper(Global.shopperID);
            model.addAttribute("shopper", shopperToUpdate);

            return "/shopper/shopper-update-form";
        }

    // Get endpoints
        /** 
         * Get all shoppers
         * 
         * @return list of all shoppers
         */
        @GetMapping
        public List<Shopper> getAllShoppers() {
            return shopperService.getAllShoppers();
        }

        /**
         * Redirect for navbar buttons
         * 
         * @return your specific profile
         */

         @GetMapping("/myprofile")
         public String getProfile() {
            return "redirect:/shoppers/" + Global.shopperID;
         }

        /**
         * Get a shopper by their ID
         * 
         * @param shopperID
         * @return a shopper
         */
        @GetMapping("/{shopperID}") 
        public String getShopper(Model model, @PathVariable Long shopperID) {
            Shopper shopper = shopperService.getShopper(shopperID);
            model.addAttribute("shopper", shopper);

            return "/shopper/shopper-profile";
        }


    // Add endpoints
        /**
         * Add a shopper into the table
         * 
         * @param Shopper
         */
        @PostMapping
        public String createShopper(Shopper newShopper) {
            shopperService.createShopper(newShopper);

            Global.shopperID = newShopper.getShopperID();    
            return "redirect:/shoppers/myprofile";
        }

    // Update endpoints
        /**
         * Update a shopper profile
         * 
         * @param shopperID
         * @param updatedShopper
         */
        @PostMapping("/update")
        public String updateShopper(Shopper updatedShopper) {
            shopperService.updateShopper(Global.shopperID, updatedShopper);
            return "redirect:/shoppers/myprofile";
        }
}
