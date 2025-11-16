package com.team2.spartanslist.offer;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.team2.spartanslist.seller.Seller;
@Repository
public interface OfferRepository extends JpaRepository<Offer, Long>{
    List<Offer> findByAvailability(boolean availability);
    List<Offer> findByAvailabilityAndSeller(boolean availability, Seller seller);
    List<Offer> findBySeller(Seller seller);
    List<Offer> findTop3ByAvailabilityTrueAndSeller(Seller seller);
    List<Offer> findByType(String type);
}