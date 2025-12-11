package com.team2.spartanslist.order;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import com.team2.spartanslist.offer.Offer;
import com.team2.spartanslist.shopper.Shopper;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Table(name = "Orders")
@Data
public class Order{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderID;

    @ManyToOne
    @JoinColumn(name = "offerID", nullable = false)
    private Offer offer;

    @ManyToOne
    @JoinColumn(name = "shopperID", nullable = false)
    private Shopper shopper;
    
    @Column
    private int status;
    /**
     * 1 = requested
     * 2 = accepted
     * 3 = rejected
     * 4 = completed
     */


    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime postDate;


     /* Constructors */


    public Order(Offer offer, Shopper shopper, int status) {
        this.offer = offer;
        this.shopper = shopper;
        this.status = status;
    }
    public Order(Long orderID, Offer offer, Shopper shopper, int status) {
        this.orderID = orderID;
        this.offer = offer;
        this.shopper = shopper;
        this.status = status;
    }

    public String getStatusAsString() {
        switch (status) {
            case 1:
                return "requested";
            case 2:
                return "accepted";
            case 3:
                return "rejected";
            case 4:
                return "completed";                    
            default:
                return "error";
        }
    }

}
