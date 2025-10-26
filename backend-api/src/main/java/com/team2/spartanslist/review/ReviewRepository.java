package com.team2.spartanslist.review;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.team2.spartanslist.offer.Offer;
import com.team2.spartanslist.shopper.Shopper;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long>{
    public List<Review> findAllByAuthor(Shopper author);
    public List<Review> findAllByOffer(Offer offer);
}