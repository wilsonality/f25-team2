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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/api/shoppers")
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
        @GetMapping("/{shopperID}") 
        public Shopper getShopper(@PathVariable Long shopperID) {
            return shopperService.getShopper(shopperID);
        }

        /**
         * Endpoint to add a shopper into the table
         * 
         * @param shopper
         */
        @PostMapping
        public String createShopper(Model model, Shopper newShopper, @RequestParam(required = false)MultipartFile shopperPicture) {
            Shopper shopper = shopperService.createShopper(newShopper, shopperPicture);
            // check for unique phone
            Shopper check = shopperService.getShopperByPhone(newShopper.getUserPhone());
            if (check != null){
                return "redirect:/shopper/register?error=failed%20to%20create%20shopper%20account";
            }
            return "redirect:/shopper/" + String.valueOf(shopper.getShopperID());
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
        public Shopper updateShopper(@PathVariable Long shopperID, @RequestBody Shopper updatedShopper, @RequestParam(required = false)MultipartFile shopperPicture) {
            return shopperService.updateShopper(shopperID, updatedShopper, shopperPicture);
        }
}
