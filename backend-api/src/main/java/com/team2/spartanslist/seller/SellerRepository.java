package com.team2.spartanslist.seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface  SellerRepository extends JpaRepository<Seller, Long> {
    Seller findByUserPhone(String userPhone);
}