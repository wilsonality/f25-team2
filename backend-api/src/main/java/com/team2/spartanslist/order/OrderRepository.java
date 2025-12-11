package com.team2.spartanslist.order;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.team2.spartanslist.offer.Offer;
import com.team2.spartanslist.seller.Seller;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long>{
    public Order findByStatus(int status);
    public List<Order> findByOffer(Offer offer);

    @Query(value = "select * from orders where sellerID = ?1", nativeQuery = true)
    public List<Order> findByOffer_Seller(Long sellerID);
    
    @Query(value = "select orders.* from orders orders inner join offer offers on orders.offerID = offers.offerID where orders.status = ?2 and offers.sellerID = ?1", nativeQuery = true)
    public List<Order> findByOffer_SellerAndStatus(Long sellerID, int status);

    @Query(value = "select * from orders where shopperID = ?1", nativeQuery = true)
    public List<Order> findByShopper_ShopperID(Long shopperID);
}
