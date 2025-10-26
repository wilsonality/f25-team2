package com.team2.spartanslist.review;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;


@Controller
@RequiredArgsConstructor
@RequestMapping("/api/reviews")
public class ReviewController {
    private final ReviewService reviewService;

    /** endpoint to add an review
     * @param review the review to add
     * @return the offer of the review
     */
    @PostMapping
    public ResponseEntity<Review> creatReview(Review review){
        return ResponseEntity.ok(reviewService.createReview(review));
    }

    /** endpoint to update a review*/
    
}
