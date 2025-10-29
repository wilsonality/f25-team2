package com.team2.spartanslist.offer;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;


@Controller
@RequiredArgsConstructor
@RequestMapping("/api/offers")
public class OfferController{
    private final OfferService offerService;

    /** endpoint to add an offer
     * 
     * @param offer the offer are going to add
     * @return
     */
    @PostMapping
    public ResponseEntity<Offer> createOffer(@Valid @RequestBody Offer offer, @RequestBody Long sellerID){
        return ResponseEntity.ok(offerService.createOffer(offer, sellerID));
    }

    /** endpoint to update an offer
     * 
     * @param offerID the id of the offer to update
     * @param nOffer the new details of the offer
     * @return
     */

    @PutMapping("/{offerID}")
    public ResponseEntity<Offer> updateOffer(@PathVariable Long offerID, @Valid @RequestBody Offer nOffer){
        return ResponseEntity.ok(offerService.updateOffer(offerID, nOffer));
    }

    /** endpoint to get an offer
     * 
     * @param offerID the id of the offer to get
     * @return
     */
    @GetMapping("/{offerID}")
    public ResponseEntity<Offer> getOfferById(@PathVariable Long offerID){
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
    public ResponseEntity<List<Offer>> deleteOffer(Long offerID){
        offerService.deleteOffer(offerID);
        return ResponseEntity.ok(offerService.getAllOffers());
    }


    /** endpoint to see all offers
     * 
     * @return all offers
     */
    @GetMapping
    public ResponseEntity<List<Offer>> getAllOffers(){
        return ResponseEntity.ok(offerService.getAllOffers());
    }

    /** endpoint to see all available offers
     * @param availability availability of the offers
     * @return
     */
    @GetMapping("/availability/{availability}")
    public ResponseEntity<List<Offer>> findByAvailability(@PathVariable String availability){
        return ResponseEntity.ok(offerService.findByAvailability(availability));
    }

    /** endpoint to get all offers of a seller
     * @param sellerID seller to get offers for
     * @return
     */
    @GetMapping("/seller/{sellerID}")
    public ResponseEntity<List<Offer>> findBySeller(@PathVariable Long sellerID){
        return ResponseEntity.ok(offerService.findBySeller(sellerID));
    }

    /** endpoint to get available offers of a seller
     * @param availability availability of the offer
     * @param sellerID seller of the offers
     * @return 
     */
    @GetMapping("/seller/{sellerID}/availability/{availability}")
    public ResponseEntity<List<Offer>> findByAvailabilityAndSeller(@PathVariable Long sellerID, @PathVariable String availability){
        return ResponseEntity.ok(offerService.findByAvailabilityAndSeller(availability, sellerID));
    }

    

}