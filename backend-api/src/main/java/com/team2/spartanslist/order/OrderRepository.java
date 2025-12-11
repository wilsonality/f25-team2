package com.team2.spartanslist.order;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.team2.spartanslist.offer.Offer;
import com.team2.spartanslist.seller.Seller;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long>{
    public List<Order> findByStatus(int status);
    public List<Order> findByOffer_SellerAndStatusIsNot(Seller seller, int status);
    public List<Order> findByOffer(Offer offer);

    // this one won't work, order has no sellerID
    @Query(value = "select r.* from orders r inner join offer f on r.offerID = f.offerID where f.sellerID = ?1", nativeQuery = true)
    public List<Order> findByOffer_Seller(Long sellerID);
    
    @Query(value = "select r.* from orders r inner join offer f on r.offerID = f.offerID where r.status = ?2 and f.sellerID = ?1", nativeQuery = true)
    public List<Order> findByOffer_SellerAndStatus(Long sellerID, int status);

    @Query(value = "select * from orders where status = ?1 and shopperID = ?2", nativeQuery = true)
    public List<Order> findByStatusAndShopperID(int status, Long shopperID);

    @Query(value = "select * from orders where shopperID = ?1", nativeQuery = true)
    public List<Order> findByShopperID(Long shopperID);
}
