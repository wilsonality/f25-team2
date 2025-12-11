package com.team2.spartanslist.review;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.team2.spartanslist.offer.Offer;
import com.team2.spartanslist.seller.Seller;
import com.team2.spartanslist.shopper.Shopper;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long>{
    public List<Review> findAllByAuthor(Shopper author);
    public List<Review> findAllByOffer(Offer offer);
    public List<Review> findAllByOffer_SellerAndReplyIsNull(Seller seller);
    public List<Review> findTop3ByOffer_SellerOrderByRatingDesc(Seller seller);

}