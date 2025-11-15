package com.team2.spartanslist.offer;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;


@Controller
@RequiredArgsConstructor
@RequestMapping("/offers")
public class OfferController{
    private final OfferService offerService;

    /** endpoint to add an offer
     * 
     * @param offer the offer to add
     * note : the offer's seller is empty, except for a sellerID.
     * we look up this ID in the service
     * @return
     */
    @PostMapping
    public Object createOffer(Model model, Offer offer){
        offerService.createOffer(offer);
        model.addAttribute("offer", offer);
        model.addAttribute("title", "Brwose Offers ");
        return "redirect:/seller/seller-offer-details";
    }

    @GetMapping("/create")
    public Object createOfferForm(Model model){
        model.addAttribute("newOffer", new Offer());
        model.addAttribute("title", "Post a new offer!");
        return "seller/seller-create-offer";
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
    @DeleteMapping ("/{offerID}")
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
    public Object findByAvailability(@PathVariable String availability){
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
    public Object findByAvailabilityAndSeller(Model model, @PathVariable Long sellerID, @PathVariable String availability){
        List<Offer> offers = offerService.findByAvailabilityAndSeller(availability, sellerID);
        return "browse-offers";
    }

    

}