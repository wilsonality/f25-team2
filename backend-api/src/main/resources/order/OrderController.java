package order;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/orders")
public class OrderController {
    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<Order> createOrder(@Valid @RequestBody Order order, Long shopperID, Long offerID){
        return ResponseEntity.ok(orderService.createOrder(order, shopperID, offerID));
    }

    @GetMapping
    public ResponseEntity<List<Order>> getAllOrders(){
        return ResponseEntity.ok(orderService.getAllOrders());
    }

    @PutMapping("/{orderID}")
    public ResponseEntity<Order> updateOrder(@PathVariable Long orderID, @Valid @RequestBody Order order){
        return ResponseEntity.ok(orderService.updateOrder(orderID, order));
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
