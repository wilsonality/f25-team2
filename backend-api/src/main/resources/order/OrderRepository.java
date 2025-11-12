package order;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.team2.spartanslist.seller.Seller;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long>{
    public Order findByStatus(int status);
    public List<Order> findByOffer_Seller(Seller seller);
    public List<Order> findByOffer_SellerAndStatus(Seller seller, int status);
    public List<Order> findByShopper_ShopperID(Long shopperID);
}
