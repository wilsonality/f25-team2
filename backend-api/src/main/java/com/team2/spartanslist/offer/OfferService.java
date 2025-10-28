package com.team2.spartanslist.offer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.team2.spartanslist.seller.Seller;
import com.team2.spartanslist.seller.SellerService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class OfferService {
    @Autowired  
    private final OfferRepository offerRepository;
    private final SellerService sellerService;

    /** method to create an offer
     * @param offer the offer to create
     * @param sellerID the id of the seller creating the offer
     * @return
     */
    
    public Offer createOffer(Offer offer, Long sellerID){
        if (offerRepository.existsById(offer.getOfferID())){
            throw new IllegalStateException("Offer already created");
        }
        offer.setSeller(sellerService.getSellerById(sellerID));
        return offerRepository.save(offer);
    }

    /** Method to update a given offer
     * 
     * @param offerID id of the offer we are updating
     * @param nOffer new offer object with user's update
     * @return saves updated offer to the repository
     */

    public Offer updateOffer(Long offerID, Offer nOffer){
        Offer offer = offerRepository.findById(offerID).orElseThrow(() -> new IllegalStateException("Offer with ID:" + offerID + " could not be found."));

        offer.setTitle(nOffer.getTitle());
        offer.setDescription(nOffer.getDescription());
        offer.setAvailability(nOffer.getAvailability());
        offer.setOffer_image(nOffer.getOffer_image());
        offer.setPrice(nOffer.getPrice());
        offer.setPayment(nOffer.getPayment());

        return offerRepository.save(offer);
    }
    
    /** method to get an offer by its id
     * 
     * @param offerID id of the offer to get
     * @return
     */
    public Offer getOfferById(Long offerID) {
        return offerRepository.findById(offerID).orElseThrow(() -> new IllegalStateException("Seller with ID:" + offerID + " could not be found."));
    }

    /** method to delete an offer by its id
     *
     * @param offerID id of the offer to delete
     */
    public void deleteOffer(Long offerID) {
        offerRepository.deleteById(offerID);
    }

    /** method to get all offers
     *
     * @return all offers
     */
    public List<Offer> getAllOffers() {
        return offerRepository.findAll();
    }

    /** method to see all available offers
     * @param availability availability of the offers
     * @return
     */
    public List<Offer> findByAvailability(String availability){
        return offerRepository.findByAvailability(availability);
    }

    /** method to get available offers of a seller
     * @param availability availability of the offer
     * @param sellerID id of the seller
     * @return 
     */
    public List<Offer> findByAvailabilityAndSeller(String availability, Long sellerID){
        Seller seller = sellerService.getSellerById(sellerID);
        return offerRepository.findByAvailabilityAndSeller(availability, seller);
    }

    /** method to get offers of a seller
     * @param sellerID the id of the seller
     * @return 
     */
    public List<Offer> findBySeller(Long sellerID){
        Seller seller = sellerService.getSellerById(sellerID);
        return offerRepository.findBySeller(seller);
    }
}