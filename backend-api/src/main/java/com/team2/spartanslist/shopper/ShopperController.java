package com.team2.spartanslist.shopper;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
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
import com.team2.spartanslist.seller.Seller;
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
    @Autowired
    private SellerService sellerService;

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

            return "shopper/shopper-registration-form";
        } 

        @GetMapping("/updateprofile")
        public String showUpdateForm(Model model, Authentication auth) {
            // if user not signed in
            if (auth == null || !auth.isAuthenticated()){
                return "redirect:/login";
            }
            Shopper user = shopperService.getShopperByPhone(auth.getName());

            String pageTitle = String.format("Edit %s's Profile'",user.getUsername());
            model.addAttribute("title", pageTitle);

            model.addAttribute("shopper", user);
            model.addAttribute("user", user);

            return "shopper/shopper-update";
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

        /** endpoint to the user's profile
         * 
         * @return user's profile
         */

         @GetMapping("/myprofile")
         public String getShopperProfilie(Model model, Authentication auth) {
            // if user not signed in
            if (auth == null || !auth.isAuthenticated()){
                return "redirect:/login";
            }

            Shopper user = shopperService.getShopperByPhone(auth.getName());

            String pageTitle = "View Your profile";
            model.addAttribute("title", pageTitle);
            model.addAttribute("user", user);
            model.addAttribute("shopper", user);

            model.addAttribute("author", true);
            model.addAttribute("cart", cartService.getCart(user));

            List<Review> reviews = reviewService.getAllReviewsByAuthorId(user.getShopperID());
            if (reviews.size() > 0){
                model.addAttribute("reviews", reviews);
            }
            
            return "shopper/shopper-details";
         }

        /**
         * Get a shopper by their ID
         * 
         * @param shopperID
         * @return a shopper
         */
        @GetMapping("/{shopperID}")
        public String getShopper(Model model, @PathVariable Long shopperID, Authentication auth) {
            Shopper shopper = shopperService.getShopper(shopperID);
            if (shopper == null){
                return "redirect:/shoppers?error=shopper%20not%20found";
            }
            String pageTitle = String.format("View %s's profile",shopper.getUsername());
            model.addAttribute("shopper", shopper);
            model.addAttribute("title", pageTitle);

            if (auth.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_SHOPPER"))){
            Shopper user = shopperService.getShopperByPhone(auth.getName());
            // if it's their own page, redirectt
            if (user.getShopperID() == shopperID){
                return "redirect:/shoppers/myprofile";
            }
            
            model.addAttribute("user", user);
        } else if (auth.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_SELLER"))){
            Seller user = sellerService.getSellerByPhone(auth.getName());
            model.addAttribute("user", user);
            return "shopper/shopper-details";
        }

            return "redirect:/home";
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
            model.addAttribute("user", user);

            String pageTitle = String.format("Welcome, %s",user.getUsername());
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


        @GetMapping("/cart")
        public Object getCart(Model model, Authentication auth) {
            // if user not signed in
            if (auth == null || !auth.isAuthenticated()){
                return "redirect:/login";
            }

            Shopper user = shopperService.getShopperByPhone(auth.getName());
            model.addAttribute("user", user);

            String pageTitle = String.format("%s's Cart",user.getUsername());
            model.addAttribute("title", pageTitle);

            // model.addAttribute("offers", cartService.getCart(user));
            model.addAttribute("items", cartService.getCart(user));
            model.addAttribute("orders", orderService.getOrdersByShopper(user.getShopperID()));

            return "shopper/shopper-cart";
        }

        @GetMapping("/mysubscriptions")
        public String getMySubscriptions() {

        return "redirect:/mailinglist/subscriptions/" + Global.shopperID;
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
                return "redirect:/shoppers/register?error=failed%20to%20create%20shopper%20account";
            }

            return "redirect:/shoppers/" + String.valueOf(shopper.getShopperID());
        }

        /** Update a shopper profile
         * 
         * @param shopperID
         * @param updatedShopper
         */
        @PostMapping("/{shopperID}")
        public String updateShopper(@PathVariable Long shopperID, Shopper nShopper, Authentication auth, @RequestParam(required = false)MultipartFile shopperPicture) {
            // if user not signed in
            if (auth == null || !auth.isAuthenticated()){
                return "redirect:/login";
            }

            Shopper user = shopperService.getShopperByPhone(auth.getName());
            // make sure users can only update their profiles
            if (user.getShopperID() != shopperID){
                return "redirect:/403";
            }
            shopperService.updateShopper(shopperID, nShopper, shopperPicture);

            return "redirect:/shoppers/" + shopperID;
        }
}
