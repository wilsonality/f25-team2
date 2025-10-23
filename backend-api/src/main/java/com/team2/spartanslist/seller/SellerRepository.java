package com.team2.spartanslist.seller;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface  SellerRepository extends JpaRepository<Seller, Long> {
    Optional<Seller> findByUserPhone(String userPhone);
    boolean existsByUserPhone(String userPhone);
}