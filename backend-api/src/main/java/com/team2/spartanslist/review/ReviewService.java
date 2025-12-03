package com.team2.spartanslist.review;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.team2.spartanslist.offer.Offer;
import com.team2.spartanslist.offer.OfferService;
import com.team2.spartanslist.seller.SellerService;
import com.team2.spartanslist.shopper.Shopper;
import com.team2.spartanslist.shopper.ShopperService;

import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
@Transactional
public class ReviewService {
    @Autowired
    private final ReviewRepository reviewRepository;
    private final ShopperService shopperService;
    private final OfferService offerService;
    private final SellerService sellerService;

    /** method to create a review
     * @param review the review to create
     * note : this review contains a offer and author that only has an ID defined.
     * we use this id to get the relevants object and set it to the review
     * @return
     */
    public Review createReview(Review review) {
        
        if (reviewRepository.existsById(review.getReviewID())){
            throw new IllegalStateException("Review already created.");
        }
        
        /*
        Shopper author = shopperService.getShopper(review.getAuthor().getShopperID());
        Offer offer = offerService.getOfferById(review.getOffer().getOfferID());

        review.setAuthor(author);
        review.setOffer(offer);
        */

        return reviewRepository.save(review);
    }

    /** method to update a review
     * @param reviewID the id of the review to update
     * @param nReview the new review details
     * @return
     */
    public Review updateReview(Long reviewID, Review nReview) {
        Review review = reviewRepository.findById(reviewID).orElseThrow(() -> new IllegalStateException("Review with ID:" + reviewID + " could not be found."));
        
        review.setAuthor(nReview.getAuthor());
        review.setOffer(nReview.getOffer());
        review.setContent(nReview.getContent());
        review.setDateTime(nReview.getDateTime());
        review.setRating(nReview.getRating());
        review.setReply(nReview.getReply());

        return reviewRepository.save(review);
    }

    /** method to delete a review
     * @param reviewID the id of the review to delete
     */
    public void deleteReview(Long reviewID) {
        reviewRepository.deleteById(reviewID);
    }

    /** method to get a review by its id
     * @param reviewID the id of the review to get
     * @return
     */
    public Review getReviewById(Long reviewID) {
        return reviewRepository.findById(reviewID).orElseThrow(() -> new IllegalStateException("Review with ID:" + reviewID + " could not be found."));
    }

    /** method to get all reviews
     * @return
     */
    public List<Review> getAllReviews() {
        return reviewRepository.findAll();
    }

    /** method to get all reviews by shopper id
     * @param shopperID the id of the shopper
     * @return
     */
    public List<Review> getAllReviewsByAuthorId(Long shopperID) {
        return reviewRepository.findAllByAuthor(shopperService.getShopper(shopperID));
    }

    /** method to get all reviews by offer id
     * @param offerID the id of the offer
     * @return
     */
    public List<Review> getAllReviewsByOfferId(Long offerID) {
        return reviewRepository.findAllByOffer(offerService.getOfferById(offerID));
    }
    
    /** method to get unreplied reviews for a seller
     * @param sellerID id of the seller
     * @return
     */
    public List<Review> getAllReviewsBySellerAndNoReply(Long sellerID){
        return reviewRepository.findAllByOffer_SellerAndReplyIsNull(sellerService.getSellerById(sellerID));
    }

    /** method to reply to a review 
     * @param reviewID the id of the review to reply to
     * @param nReview review object with reply only
     * @return
     */
    public Review replyToReview(Long reviewID, Review nReview){
        Review review = reviewRepository.findById(reviewID).orElseThrow(() -> new IllegalStateException("Review with ID:" + reviewID + " could not be found."));
        review.setReply(nReview.getReply());
        return reviewRepository.save(review);
    }

    // TODO: endpoint for getting the three top rated completed reviews, this is for displaying a profile
}
