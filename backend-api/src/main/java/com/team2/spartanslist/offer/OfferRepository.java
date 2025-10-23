package com.team2.spartanslist.offer;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface OfferRepository extends JpaRepository<Offer, Long>{
    List<Offer> findByAvailability(String availability);
}