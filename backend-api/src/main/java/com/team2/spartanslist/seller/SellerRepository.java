package com.team2.spartanslist.seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface  SellerRepository extends JpaRepository<Seller, Long> {
    
    /** method to find seller by phone number
     * 
     * @param userPhone phone number of seller to search
     * @return
     */
    Seller findByUserPhone(String userPhone);
}