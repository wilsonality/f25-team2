package com.team2.spartanslist.offer;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.team2.spartanslist.Global;
import com.team2.spartanslist.cart.CartService;
import com.team2.spartanslist.order.Order;
import com.team2.spartanslist.order.OrderService;
import com.team2.spartanslist.review.Review;
import com.team2.spartanslist.review.ReviewService;
import com.team2.spartanslist.seller.Seller;
import com.team2.spartanslist.seller.SellerService;
import com.team2.spartanslist.shopper.Shopper;
import com.team2.spartanslist.shopper.ShopperService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;


@Controller
@RequiredArgsConstructor
@RequestMapping("/offers")
public class OfferController{
    private final OfferService offerService;
    private final SellerService sellerService;
    private final OrderService orderService;
    private final ReviewService reviewService;
    private final ShopperService shopperService;
    private final CartService cartService;

    @GetMapping("/create")
    public Object createOfferForm(Model model, Authentication auth){
        // if user not signed in
        if (auth == null || !auth.isAuthenticated()){
            return "redirect:/login";
        }

        Seller user = sellerService.getSellerByPhone(auth.getName());

        model.addAttribute("user", user);
        model.addAttribute("newOffer", new Offer());
        model.addAttribute("title", "Post a new offer!");
        return "seller/create-offer";
    }

    
    /** endpoint to add an offer
     * 
     * @param newOffer the offer to add
     * @param sellerID the id of the seller to add the offer to
     * @param model
     * note : the offer's seller is empty, except for a sellerID.
     * we look up this ID in the service
     * @return
     */
    @PostMapping
    public Object createOffer(Model model, Offer newOffer, Authentication auth, @RequestParam(required = false) MultipartFile offerPicture){
        Seller seller = sellerService.getSellerByPhone(auth.getName());
        System.out.println("pulling seller " + seller);
        newOffer.setSeller(seller);
        Offer offer = offerService.createOffer(newOffer, offerPicture);


        return "redirect:/offers/" + offer.getOfferID();
    }

    @GetMapping("/{offerID}/update")
    public Object updateForm(Model model, @PathVariable Long offerID, Authentication auth){
        // if user not signed in
        if (auth == null || !auth.isAuthenticated()){
            return "redirect:/login";
        }
        Seller user = sellerService.getSellerByPhone(auth.getName());
        model.addAttribute("user", user);

        Offer offer = offerService.getOfferById(offerID);

        // if user not the author
        if (user.getSellerID() != offer.getSeller().getSellerID()){
            return "redirect:/403";
        }

        model.addAttribute("offer", offer);
        model.addAttribute("title", "Update Offer");
        return "seller/update-offer";
    }
    
    /** endpoint to update an offer
     * 
     * @param offerID the id of the offer to update
     * @param nOffer the new details of the offer
     * @return
     */

    @PostMapping("/{offerID}")
    public Object updateOffer(@PathVariable Long offerID, Offer nOffer, Authentication auth, @RequestParam(required = false) MultipartFile offerPicture){
        // if user not signed in
        if (auth == null || !auth.isAuthenticated()){
            return "redirect:/login";
        }
        Seller user = sellerService.getSellerByPhone(auth.getName());

        Offer existing = offerService.getOfferById(offerID);
        // make sure users can only update their offers
        if (user.getSellerID() != existing.getSeller().getSellerID()){
            return "redirect:/403";
        }

        System.out.println("\n\nDEBUG ::: EXECUTING OFFERCONTROLLER.UPDATEOFFER()");

        offerService.updateOffer(offerID, nOffer, offerPicture);
        return "redirect:/offers/" + offerID;
    }

    /** endpoint to get an offer
     * checks kind of user viewing, checks if author is viewing
     * 
     * @param offerID the id of the offer to get
     * @return offer details, author details, reviews, orders (if author)
     */
    @GetMapping("/{offerID}")
    public Object getOfferById(Model model, @PathVariable Long offerID, Authentication auth){
        Offer offer = offerService.getOfferById(offerID);
        if (offer == null){
            model.addAttribute("error", "Sorry, this offer could not be found.");
            return "error";
        }

        String pageTitle = String.format("View Offer: %s", offer.getTitle());
        model.addAttribute("title", pageTitle);
        model.addAttribute("offer", offer);
        model.addAttribute("seller", offer.getSeller());
        // if user is seller
        if (auth.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_SELLER"))){
            Seller user = sellerService.getSellerByPhone(auth.getName());
            model.addAttribute("user", user);

            // if user is author of this offer
            if (user.getUserPhone() == auth.getName()){
                model.addAttribute("author", true);
                List<Order> orders = orderService.getOrdersByOffer(offerID);
                if (orders.size() > 0){
                    model.addAttribute("requests", orders);
                }

                List<Review> reviews = reviewService.getAllReviewsByOfferId(offerID);
                if (reviews.size() > 0){
                    model.addAttribute("reviews", reviews);
                }
            }
            return "seller/seller-view-offer";
        } else if (auth.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_SHOPPER"))){
            Shopper user = shopperService.getShopperByPhone(auth.getName());
            model.addAttribute("user", user);
            model.addAttribute("incart", false);
            return "shopper/shopper-view-offer";
        }

        return "shopper/shopper-view-offer";
    }

    /** endpoint to see delete confirm page
     * 
     * @param offerID the id of the offer to delete
     * @return all offers
     */
    @GetMapping ("{offerID}/delete")
    public Object deletePage(Long offerID){
        offerService.deleteOffer(offerID);
        return ResponseEntity.ok(offerService.getAllOffers());
    }

    /** endpoint to delete an offer
     * future note: add a check to see if the user is the owner of the offer
     * then return only their offers
     * 
     * @param offerID the id of the offer to delete
     * @return all offers
     */
    @PostMapping ("{offerID}/delete")
    public Object deleteOffer(Long offerID){
        offerService.deleteOffer(offerID);
        return ResponseEntity.ok(offerService.getAllOffers());
    }


    /** endpoint to see all offers
     * 
     * @return all offers
     */
    @GetMapping
    public Object getAllOffers(){
        return ResponseEntity.ok(offerService.getAllOffers());
    }

    /** endpoint to see all available offers
     * @param availability availability of the offers
     * @return
     */
    @GetMapping("/availability/{availability}")
    public Object findByAvailability(@PathVariable boolean availability){
        return ResponseEntity.ok(offerService.findByAvailability(availability));
    }


    /*
     * Redirect for nav
     */
    @GetMapping("/myoffers") 
    public String getSellerOffers(Model model, Authentication auth) {
        // if user not signed in
        if (auth == null || !auth.isAuthenticated()){
            return "redirect:/login";
        }

        Seller seller = sellerService.getSellerByPhone(auth.getName());
        model.addAttribute("user", seller);
        Long sellerID = seller.getSellerID();

        model.addAttribute("title", "View Your Offers");

        List<Offer> offers = offerService.findBySeller(sellerID);
        model.addAttribute("offers", offers);

        return "seller/seller-view-offers";

    }
    
    /** endpoint to get all offers of a seller
     * @param sellerID seller to get offers for
     * @return
     */
    @GetMapping("/seller/{sellerID}")
    public Object findBySeller(Model model, @PathVariable Long sellerID){
        List<Offer> offers = offerService.findBySeller(sellerID);
        String pageTitle = String.format("All Offers By %s", offers.get(0).getSeller().getUsername());
        model.addAttribute("title", pageTitle);
        model.addAttribute("seller", sellerService.getSellerById(sellerID));
        model.addAttribute("offers", offers);
        return "seller/seller-view-offers";
    }

    /** endpoint to get available offers of a seller
     * @param availability availability of the offer
     * @param sellerID seller of the offers
     * @return 
     */
    @GetMapping("/seller/{sellerID}/availability/{availability}")
    public Object findByAvailabilityAndSeller(Model model, @PathVariable Long sellerID, @PathVariable boolean availability){
        List<Offer> offers = offerService.findByAvailabilityAndSeller(availability, sellerID);
        model.addAttribute("offers", offers);
        String pageTitle = String.format("All Offers By %s", offers.get(0).getSeller().getUsername());  
        model.addAttribute("title", pageTitle);
        return "browse-offers";
    }

    /** endpoint to get all offers of a type
     * @param type type of the offer
     * @return
     */
    @GetMapping("/type/{type}")
    public Object findByType(@PathVariable String type){
        return ResponseEntity.ok(offerService.findByType(type));
    }
}