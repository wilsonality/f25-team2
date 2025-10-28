package com.team2.spartanslist.order;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface  OrderRepository extends JpaRepository<Order, Long>{
    public Order findByStatus(int status);
    public List<Order> findByOffer_Seller_SellerIDAndStatus(Long sellerID, int status);
    public List<Order> findByOffer_Seller_SellerID(Long sellerID);
    public List<Order> findByShopper_ShopperID(Long shopperID);
}
