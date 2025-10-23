package com.team2.spartanslist.offer;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class OfferService {
    private final OfferRepository offerRepository;

    public Offer getOfferById(Long id) {
        return offerRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }
    
    public Offer createOffer(Offer offer){
        if (offerRepository.existsById(offer.getOfferID())){
            throw new IllegalStateException("Offer already created");
        }
        return offerRepository.save(offer);
    }

    public Offer updateOffer(Long offerID,Offer nOffer){
        Offer offer = offerRepository.findById(offerID).orElseThrow(EntityNotFoundException::new);

        offer.setTitle(nOffer.getTitle());
        
        return offerRepository.save(offer);
    }

}


public Farmer updateFarmer(Long id, Farmer farmerDetails) {
        Farmer farmer = farmerRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Farmer not found"));

        farmer.setEmail(farmerDetails.getEmail());
        farmer.setPhoneNumber(farmerDetails.getPhoneNumber());

        return farmerRepository.save(farmer);
    }