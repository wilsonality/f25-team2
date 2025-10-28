package com.team2.spartanslist.offer;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
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
    public ResponseEntity<Offer> createOffer(@Valid @RequestBody Offer offer){
        return ResponseEntity.ok(offerService.createOffer(offer));
    }
}