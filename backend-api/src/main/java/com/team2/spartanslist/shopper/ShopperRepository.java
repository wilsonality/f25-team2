package com.team2.spartanslist.shopper;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShopperRepository extends JpaRepository<Shopper, Long> {
}
