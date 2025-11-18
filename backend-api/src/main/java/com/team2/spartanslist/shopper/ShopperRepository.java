package com.team2.spartanslist.shopper;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ShopperRepository extends JpaRepository<Shopper, Long> {
    @Query(value = "select * from shopper where username = ?1", nativeQuery = true)
    Shopper findByUsername(String username);
}
