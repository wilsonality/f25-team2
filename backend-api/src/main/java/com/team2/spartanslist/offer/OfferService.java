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
     * note : this offer contains a seller that only has an ID defined.
     * we use this id to get the seller object and set it to the offer
     * @return
     */
    
    public Offer createOffer(Offer offer){
        System.out.println("DEBUG ::: ENTERING OFFER SERVICE");
        System.out.println("DEBUG :: TRYING TO SAVE OFFER: " + offer);

        if (offer.getOfferImagePath() == null){
            offer.setOfferImagePath("default.jpg");
            System.out.println("DEBUG :: SAVED OFFER IMAGE PATH");
        }

        System.out.println("DEBUG ::: SAVING OFFER TO REPOSITORY");
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
        offer.setAvailability(nOffer.isAvailability());
        offer.setOfferImagePath(nOffer.getOfferImagePath());
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
        List<Offer> offers = offerRepository.findAll();
        System.out.println("getAllOffers() returned " + offers.size() + " offers");
        return offers;
    }

    /** method to see all available offers
     * @param availability availability of the offers
     * @return
     */
    public List<Offer> findByAvailability(boolean availability){
        return offerRepository.findByAvailability(availability);
    }

    /** method to get available offers of a seller
     * @param availability availability of the offer
     * @param sellerID id of the seller
     * @return 
     */
    public List<Offer> findByAvailabilityAndSeller(boolean availability, Long sellerID){
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

    /** method to get three available offers of a seller 
     * @param sellerID the id of the seller
     * @return
    */
    public List<Offer> findByAvailableAndSellerLimitThree(Long sellerID){
        Seller seller = sellerService.getSellerById(sellerID);
        return offerRepository.findTop3ByAvailabilityTrueAndSeller( seller);
    }

    /** method to get offers of a type
     * @param type type of the offer ("item" or "service")
     * @return
     */
    public List<Offer> findByType(String type){
        return offerRepository.findByType(type);
    }
}