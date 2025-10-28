package com.team2.spartanslist.offer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OfferService {
    @Autowired  
    private final OfferRepository offerRepository;

    /** method to create an offer
     * @param offer the offer to create
     * @return
     */
    
    public Offer createOffer(Offer offer){
        if (offerRepository.existsById(offer.getOfferID())){
            throw new IllegalStateException("Offer already created");
        }
        return offerRepository.save(offer);
    }
}