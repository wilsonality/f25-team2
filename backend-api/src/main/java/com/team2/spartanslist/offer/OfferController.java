package com.team2.spartanslist.offer;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

    @GetMapping("/create")
    public Object createOfferForm(Model model){
        model.addAttribute("newOffer", new Offer());
        model.addAttribute("title", "Post a new offer!");
        return "seller/seller-create-offer";
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
    public Object createOffer(Model model, @ModelAttribute Offer newOffer, @RequestParam Long sellerID){
        try {
            System.out.println("DEBUG ::: SETTING SELLER BY SELLER ID " + sellerID);
            newOffer.setSeller(sellerService.getSellerById(sellerID));
            System.out.println("DEBUG ::: SETTING NUM PURCHASED TO ZERO");
            newOffer.setNumPurchased(0);
            // debugging
            System.out.println("DEBUG ::: ENTERING OFFER CONTROLLER");
            System.out.println("About to call getSellerById with: " + sellerID);
            Seller seller = sellerService.getSellerById(sellerID);
            System.out.println("DEBUG ::: RETRIEVED seller: " + seller);


            System.out.println("DEBUG ::: SAVING TO OFFER-SERVICE");
            Offer saved = offerService.createOffer(newOffer);
            System.out.println("DEBUG ::: SAVED TO OFFER-SERVICE");


            model.addAttribute("offer", saved);
            model.addAttribute("title", "View Offer");
            System.out.println("DEBUG ::: REDIRECTING TO OFFER PAGE");
            return "redirect:/offers/" + saved.getOfferID();
        } catch (Exception e) {
            model.addAttribute("title", "an error occured");
            model.addAttribute("error", e.getMessage());
            return "error";
        }
        // System.out.println("Received sellerID: " + sellerID);
        // return "error";
    }

    
    /** endpoint to update an offer
     * 
     * @param offerID the id of the offer to update
     * @param nOffer the new details of the offer
     * @return
     */

    @PostMapping("/{offerID}")
    public Object updateOffer(@PathVariable Long offerID, @Valid @RequestBody Offer nOffer){
        return ResponseEntity.ok(offerService.updateOffer(offerID, nOffer));
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

    /** endpoint to get all offers of a seller
     * @param sellerID seller to get offers for
     * @return
     */
    @GetMapping("/seller/{sellerID}")
    public Object findBySeller(@PathVariable Long sellerID){
        return ResponseEntity.ok(offerService.findBySeller(sellerID));
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