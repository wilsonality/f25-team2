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
import com.team2.spartanslist.cart.Cart;
import com.team2.spartanslist.cart.CartRepository;
import com.team2.spartanslist.cart.CartService;
import com.team2.spartanslist.offer.Offer;
import com.team2.spartanslist.offer.OfferRepository;
import com.team2.spartanslist.offer.OfferService;
import com.team2.spartanslist.review.Review;
import com.team2.spartanslist.review.ReviewService;
import com.team2.spartanslist.seller.SellerRepository;
import com.team2.spartanslist.seller.SellerService;

@Controller
@RequestMapping("/shoppers")
public class ShopperController {
    @Autowired
    private ShopperService shopperService;
    @Autowired
    private OfferService offerService;
    @Autowired
    private ReviewService reviewService;
    @Autowired
    private CartService cartService;
    @Autowired
    private CartRepository cartRepository;

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

        @GetMapping("/home")
        public String getOffers(Model model) {
            List<Offer> offers = offerService.getAllOffers();
            model.addAttribute("offers", offers);

            return "/shopper/shopper-home";
        }

        @GetMapping("/offer/{offerID}")
        public String getOffer(Model model, @PathVariable Long offerID) {
            Offer offer = offerService.getOfferById(offerID);
            List<Review> reviews = reviewService.getAllReviewsByOfferId(offerID);
            Review newReview = new Review();

            model.addAttribute("offer", offer);
            model.addAttribute("reviews", reviews);
            model.addAttribute(newReview);
            model.addAttribute("shopperID", Global.shopperID);

            return "/shopper/shopper-view-offer";
        }

        @GetMapping("/cart")
        public Object getCart(Model model) {
            model.addAttribute("offers", cartService.getCart());
            return "/shopper/shopper-cart";
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
