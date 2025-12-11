package com.team2.spartanslist.offer;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.team2.spartanslist.Global;
import com.team2.spartanslist.order.Order;
import com.team2.spartanslist.order.OrderService;
import com.team2.spartanslist.seller.Seller;
import com.team2.spartanslist.seller.SellerService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;


@Controller
@RequiredArgsConstructor
@RequestMapping("/offers")
public class OfferController{
    private final OfferService offerService;
    private final SellerService sellerService;
    private final OrderService orderService;

    @GetMapping("/create")
    public Object createOfferForm(Model model, Authentication auth){
        // if user not signed in
        if (auth == null || !auth.isAuthenticated()){
            return "redirect:/login";
        }

        Seller user = sellerService.getSellerByPhone(auth.getName());

        model.addAttribute("seller", user);
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
    public Object updateOfferForm(Model model, @PathVariable Long offerID, Authentication auth){
        // if user not signed in
        if (auth == null || !auth.isAuthenticated()){
            return "redirect:/login";
        }
        Seller user = sellerService.getSellerByPhone(auth.getName());
        model.addAttribute("seller", user);

        Offer offer = offerService.getOfferById(offerID);

        // if user not the author
        if (user.getSellerID() != offer.getSeller().getSellerID()){
            return "redirect:/403";
        }

        model.addAttribute("offer", offer);
        model.addAttribute("orders", orderService.getOrdersBySeller(offerID));
        model.addAttribute("title", "Update Your Offer");
        return "seller/seller-update-offer";
    }
    
    /** endpoint to update an offer
     * 
     * @param offerID the id of the offer to update
     * @param nOffer the new details of the offer
     * @return
     */

    @PostMapping("/{offerID}")
    public Object updateOffer(@PathVariable Long offerID, @Valid @RequestBody Offer nOffer, @RequestParam(required = false) MultipartFile offerPicture){
        return ResponseEntity.ok(offerService.updateOffer(offerID, nOffer, offerPicture));
    }

    /** endpoint to get an offer
     * 
     * @param offerID the id of the offer to get
     * @return
     */
    @GetMapping("/{offerID}")
    public Object getOfferById(@PathVariable Long offerID){
        return ResponseEntity.ok(offerService.getOfferById(offerID));
    }

    /** endpoint to get an offer (seller view)
     * 
     * @param offerID the id of the offer to get
     * @return
     */
    @GetMapping("/{offerID}/seller")
    public Object getOfferByIdSeller(Model model, @PathVariable Long offerID){
        Offer offer = offerService.getOfferById(offerID);
        if (offer == null){
            model.addAttribute("error", "Sorry, this offer could not be found.");
            return "error";
        }
        String pageTitle = String.format("View Offer: %s", offer.getTitle());
        model.addAttribute("title", pageTitle);
        model.addAttribute("offer", offer);
        model.addAttribute("seller", offer.getSeller());
        List<Order> requests = orderService.getOrdersByOffer(offerID);
        if (requests != null){
            model.addAttribute("requests", requests);
        }
        return "seller/seller-view-offer";
    }

    /** endpoint to delete an offer
     * future note: add a check to see if the user is the owner of the offer
     * then return only their offers
     * 
     * @param offerID the id of the offer to delete
     * @return all offers
     */
    @GetMapping ("delete/{offerID}")
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
    public String getMyoffers() {
        return "/offers/seller/" + Global.sellerID;
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