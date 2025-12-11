package com.team2.spartanslist.shopper;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
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

import com.team2.spartanslist.Global;
import com.team2.spartanslist.cart.Cart;
import com.team2.spartanslist.cart.CartRepository;
import com.team2.spartanslist.cart.CartService;
import com.team2.spartanslist.offer.Offer;
import com.team2.spartanslist.offer.OfferRepository;
import com.team2.spartanslist.offer.OfferService;
import com.team2.spartanslist.order.Order;
import com.team2.spartanslist.order.OrderRepository;
import com.team2.spartanslist.order.OrderService;
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
    @Autowired
    private OrderService orderService;

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

        // TODO : add auth
        /** endpoint to the user's profile
         * 
         * @return user's profile
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

        /** endpoint to show shopper homepage
         * @param model
         * @param auth
         * @return
         */
        @GetMapping("/home")
        public String showShopperHome(Model model, Authentication auth) {
            // if user not signed in
            if (auth == null || !auth.isAuthenticated()){
                return "redirect:/login";
            }

            Shopper user = shopperService.getShopperByPhone(auth.getName());
            model.addAttribute("shopper", user);

            String pageTitle = String.format("Welcome, %s.",user.getUsername());
            model.addAttribute("title", pageTitle);

            List<Offer> offers = offerService.getAllOffers();
            if (offers.size() != 0){
                model.addAttribute("offers", offers);
            }

            List<Order> requests = orderService.getOrdersbyShopperIDAndStatus(user.getShopperID(), 1);
            if (requests.size() != 0){
                model.addAttribute("requests", requests);
            }

            return "shopper/shopper-home";
        }

        // TODO - get rid of and add auth to offer controller
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
            model.addAttribute("orders", orderService.getOrdersByShopper(Global.shopperID));

            return "/shopper/shopper-cart";
        }


        /** endpoint to create a shopper
         * 
         * @param Shopper
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

        /** Update a shopper profile
         * 
         * @param shopperID
         * @param updatedShopper
         */
        @PutMapping("/update/{shopperID}")
        public Shopper updateShopper(@PathVariable Long shopperID, @RequestBody Shopper updatedShopper, @RequestParam(required = false)MultipartFile shopperPicture) {
            return shopperService.updateShopper(shopperID, updatedShopper, shopperPicture);
        }
}
