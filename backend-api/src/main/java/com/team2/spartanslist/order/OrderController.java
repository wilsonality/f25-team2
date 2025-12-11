package com.team2.spartanslist.order;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.team2.spartanslist.Global;
import com.team2.spartanslist.cart.CartService;
import com.team2.spartanslist.offer.OfferService;
import com.team2.spartanslist.shopper.ShopperService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/orders")
public class OrderController {
    @Autowired
    private final OrderService orderService;
    @Autowired
    OfferService offerService;
    @Autowired
    ShopperService shopperService;
    @Autowired
    CartService cartService;

    @PostMapping("/{offerID}")
    public String createOrder(@PathVariable Long offerID){
        Order order = new Order();
        ResponseEntity.ok(orderService.createOrder(order, offerID));
        cartService.deleteFromCart(offerID);

        return "redirect:/shoppers/cart";
    }

    @GetMapping
    public ResponseEntity<List<Order>> getAllOrders(){
        return ResponseEntity.ok(orderService.getAllOrders());
    }

    @PutMapping("/{orderID}")
    public ResponseEntity<Order> updateOrder(@PathVariable Long orderID, @Valid @RequestBody Order order){
        return ResponseEntity.ok(orderService.updateOrder(orderID, order));
    }

    @GetMapping("/delete/{orderID}")
    public String deleteOrder(@PathVariable Long orderID) {
        orderService.deleteOrder(orderID);

        return "redirect:/shoppers/cart";
    }

    @GetMapping("/{orderID}")
    public ResponseEntity<Order> getOrder(@PathVariable Long orderID){
        return ResponseEntity.ok(orderService.getOrder(orderID));
    }

    @GetMapping("/seller/{sellerID}")
    public ResponseEntity<List<Order>> getOrdersBySeller(@PathVariable Long sellerID){
        return ResponseEntity.ok(orderService.getOrdersBySeller(sellerID));
    }

    @GetMapping("/shopper/{shopperID}")
    public ResponseEntity<List<Order>> getOrdersByShopper(@PathVariable Long shopperID){
        return ResponseEntity.ok(orderService.getOrdersByShopper(shopperID));
    }
}
