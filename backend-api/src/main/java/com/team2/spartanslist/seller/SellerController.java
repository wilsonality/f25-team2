package com.team2.spartanslist.seller;

import java.util.List;

import org.hibernate.JDBCException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.team2.spartanslist.shopper.Shopper;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/sellers")
public class SellerController{
    private final SellerService sellerService;

    // Show forms endpoints
        /**
         * Endpoint to show seller registration form
         * 
         * @param model
         * @return
         */
        @GetMapping("/register")
        public String showRegisterForm(Model model) {
            Seller newSeller = new Seller();
            model.addAttribute(newSeller);
            return "/seller/seller-registration-form";
        } 

        /** endpoint to show seller update form
         * @param model
         * @param sellerID
         * @return
         */
        @GetMapping("/updateForm/{sellerID}")
        public Object showSellerUpdateForm(Model model, @PathVariable long sellerID){
            return "seller-update";
        }

    // Add and delete endpoints
        /** endpoint to add a seller
         * 
         * @param seller the seller to be added
         * @param model
         * @return
         */
        @PostMapping
        public String createSeller(Seller newSeller) {
            sellerService.createSeller(newSeller);
            Long sellerID = newSeller.getSellerID();

            return "redirect:/api/sellers/" + sellerID + "/profile";
        }
    
    // Get endpoints
        /** endpoint to get all sellers
         * @param model
         * @return
         */
        @GetMapping
        public Object getAllSellers(Model model){
            model.addAttribute("sellers", sellerService.getAllSellers());
            model.addAttribute("title", "View All Sellers");
            return "sellers-list";
        }

        /** endpoint to get a seller by id
         * 
         * @param sellerID id of the seller to get
         * @return
         */
        @GetMapping("/{sellerID}/profile")
        public Object getSellerById(Model model, @PathVariable Long sellerID){
            Seller seller = sellerService.getSellerById(sellerID);
            String pageTitle = String.format("View %s's profile",seller.getUsername());
            model.addAttribute("seller", seller);
            model.addAttribute("title", pageTitle);

            return "seller/seller-profile";
        }

        @GetMapping("/seller/{userPhone}")
        public Object getSellerByPhone(Model model, @PathVariable String userPhone){
            Seller seller = sellerService.getSellerByPhone(userPhone);
            String pageTitle = String.format("View %s's profile",seller.getUsername());
            model.addAttribute("seller", seller);
            model.addAttribute("title", pageTitle);
            return "seller-details";
        }


    // Update Endpoints
        /** endpoint to update a seller
         * 
         * @param sellerID the id of the seller to be updated
         * @param nSeller the new details of the seller
         * @return
         */
        @PostMapping("/{sellerID}")
        public Object updateSeller(@PathVariable Long sellerID, Seller nSeller){
            sellerService.updateSeller(sellerID, nSeller);
            return "seller-details";
        }

        /** endpoint to delete a seller
         * 
         * @param model
         * @param sellerID the id of the seller to be deleted
         * @return
         */
        @GetMapping("/delete/{sellerID}")
        public Object deleteSeller(Model model, @PathVariable Long sellerID){
            model.addAttribute("sellers", sellerService.getAllSellers());
            model.addAttribute("title", "View All Sellers");
            return "sellers-list";
        }
}