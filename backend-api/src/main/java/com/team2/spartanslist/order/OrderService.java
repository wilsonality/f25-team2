package com.team2.spartanslist.order;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.team2.spartanslist.offer.OfferService;
import com.team2.spartanslist.seller.SellerService;
import com.team2.spartanslist.shopper.ShopperService;

import lombok.RequiredArgsConstructor;


@Transactional
@Service
@RequiredArgsConstructor
public class OrderService {
    @Autowired
    private final OrderRepository orderRepository;
    @Autowired
    private final ShopperService shopperService;
    @Autowired
    private final OfferService offerService;
    @Autowired
    private final SellerService sellerService;

    /** method to create an order 
     * @param order to create
     * @param shopperID shopper making the order
     * @param offerID offer being ordered
     * @return
    */
    
    public Order createOrder(Order order, Long shopperID, Long offerID){
        if (orderRepository.existsById(order.getOrderID())){
            throw new IllegalStateException("Order already created");
        }
        order.setShopper(shopperService.getShopper(shopperID));
        order.setOffer(offerService.getOfferById(offerID));
        return orderRepository.save(order);
    }
    /** method to update an order
     * @param orderID order to update
     * @param order order to update
     * @return
     */
    public Order updateOrder(Long orderID, Order order){
        Order o = orderRepository.findById(orderID).orElseThrow(() -> new IllegalStateException("Order not found"));
        o.setStatus(order.getStatus());
        return orderRepository.save(o);
    }

    /** method to get an order
     * @param orderID order to get
     * @return
     */
    public Order getOrder(Long orderID){
        return orderRepository.findById(orderID).orElseThrow(() -> new IllegalStateException("Order not found"));
    }

    /** method to get all reviews
     * @return
     */
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    /** method to get orders by seller 
     * @param sellerID seller to get orders for
     * @return
     */
    public List<Order> getOrdersBySeller(Long sellerID){
        return orderRepository.findByOffer_Seller(sellerService.getSellerById(sellerID));
    }

    /** method to get orders by shopper
     * @param shopperID shopper to get orders for
     * @return
     */
    public List<Order> getOrdersByShopper(Long shopperID){
        return orderRepository.findByShopper_ShopperID(shopperID);
    }

    /** method to get orders by shopper and status
     * @param shopperID shopper to get orders for
     * @param status the status of the orders to get
     * @return
    */

    /** method to get unanswered orders for a seller 
     * @param sellerID seller to get orders for
     * @param status the status of the orders to get
     * @return
    */
    public List<Order> getOrdersbySellerAndStatus(Long sellerID, int status){
        return orderRepository.findByOffer_SellerAndStatus(sellerService.getSellerById(sellerID), status);
    }



    
}
