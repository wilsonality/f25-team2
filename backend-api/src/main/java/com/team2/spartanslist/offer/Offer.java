package com.team2.spartanslist.offer;

import com.team2.spartanslist.seller.Seller;

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
@Data
@Table(name = "Offer")
public class Offer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long offerID;

    @ManyToOne
    @JoinColumn(name="sellerID",nullable = false)
    private Seller seller;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String availability;

    @Column(nullable = false)
    private String description;
    
    private String offer_image;
    private int type;
    private int price;
    private String payment;
    private int numPurchased;

    /*
     * Constructors
     */

    public Offer(Long offerID, String title, String availability, String description, String offer_image, int type, int price, String payment, int numPurchased) {
        this.offerID = offerID;
        this.title = title;
        this.availability = availability;
        this.description = description;
        this.offer_image = offer_image;
        this.type = type;
        this.price = price;
        this.payment = payment;
        this.numPurchased = numPurchased;
    }
    public Offer(String title, String availability, String description, String offer_image, int type, int price, String payment, int numPurchased) {
        this.title = title;
        this.availability = availability;
        this.description = description;
        this.offer_image = offer_image;
        this.type = type;
        this.price = price;
        this.payment = payment;
        this.numPurchased = numPurchased;
    }
    public Offer(String title, String availability, String description, String offer_image, int type, int price, String payment) {
        this.title = title;
        this.availability = availability;
        this.description = description;
        this.offer_image = offer_image;
        this.type = type;
        this.price = price;
        this.payment = payment;
        this.numPurchased = 0;
    }
}
