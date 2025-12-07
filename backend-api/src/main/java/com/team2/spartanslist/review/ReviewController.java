package com.team2.spartanslist.review;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;

import com.team2.spartanslist.Global;
import com.team2.spartanslist.offer.Offer;
import com.team2.spartanslist.offer.OfferService;
import com.team2.spartanslist.shopper.Shopper;
import com.team2.spartanslist.shopper.ShopperService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;


@Controller
@RequiredArgsConstructor
@RequestMapping("/api/reviews")
public class ReviewController {
    @Autowired
    private final ReviewService reviewService;
    @Autowired
    ShopperService shopperService;
    @Autowired 
    OfferService offerService;

    /** endpoint to add an review
     * @param review the review to add
     * @return the offer of the review
     */
    @PostMapping
    public String createReview(Review review, Long offerID){
        Shopper author = shopperService.getShopper(Global.shopperID);
        Offer offer = offerService.getOfferById(offerID);

        review.setAuthor(author);
        review.setOffer(offer);

        reviewService.createReview(review);

        return "redirect:/shoppers/offer/" + offerID;
    }

    /** endpoint to update a review
     * @param reviewID the id of the review to update 
     * @param nReview the new details of the review
     * @return
    */
    @PutMapping("/{reviewID}")
    public ResponseEntity<Review> updateReview(@PathVariable Long reviewID, @Valid @RequestBody Review nReview){
        return ResponseEntity.ok(reviewService.updateReview(reviewID, nReview));
    }

    /** endpoint to get a review
     * @param reviewID the id of the review to get
     * @return
    */
    @GetMapping("/{reviewID}")
    public ResponseEntity<Review> getReviewById(@PathVariable Long reviewID){
        return ResponseEntity.ok(reviewService.getReviewById(reviewID));
    }

    /** endpoint to delete a review
     * @param reviewID the id of the review to delete
     * @return all reviews
    */
    @PostMapping("/{reviewID}")
    public ResponseEntity<List<Review>> deleteReview(@PathVariable Long reviewID){
        reviewService.deleteReview(reviewID);
        return ResponseEntity.ok(reviewService.getAllReviews());    
    }

    /** endpoint to get all reviews
     * @return all reviews
    */
    @GetMapping
    public ResponseEntity<List<Review>> getAllReviews(){
        return ResponseEntity.ok((reviewService.getAllReviews()));
    }

    /** endpoint to get all reviews by shopper id
     * @param shopperID the id of the shopper
     * @return
    */
    @GetMapping("/all/shopper/{shopperID}")
    public ResponseEntity<List<Review>> getAllReviewsByShopperId(@PathVariable Long shopperID){
        return ResponseEntity.ok(reviewService.getAllReviewsByAuthorId(shopperID));
    }

    /** endpoint to get all reviews by offer id
     * @param offerID the id of the offer
     * @return
    */
    @GetMapping("/all/offer/{offerID}")
    public ResponseEntity<Review> getAllReviewsByOfferId(@PathVariable Long offerID){
        return ResponseEntity.ok((Review) reviewService.getAllReviewsByOfferId(offerID));
    }

    /** endpoint to get all offers of a seller with no reply */
    @GetMapping("/seller/{sellerID}/noreply")
    public ResponseEntity<List<Review>> getAllReviewsBySellerAndNoReply(@PathVariable Long sellerID){
        return ResponseEntity.ok(reviewService.getAllReviewsBySellerAndNoReply(sellerID));
    }

    @PostMapping("/{reviewID}/reply")
    public ResponseEntity<Review> replyToReview(@PathVariable Long reviewID, @Valid @RequestBody Review reply){
        return ResponseEntity.ok(reviewService.replyToReview(reviewID, reply));
    }
    
}