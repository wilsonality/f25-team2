package com.team2.spartanslist.offer;

import java.beans.ConstructorProperties;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import java.util.ArrayList;
import java.util.List;

import com.team2.spartanslist.seller.Seller;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
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
    @JoinColumn(name = "sellerID", nullable = false)
    private Seller seller;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private boolean availability;

    @Column(nullable = false)
    private String description;
    
    private String offerImagePath;

    private String type;

    private int price;

    private String payment;

    @Column(nullable = false, columnDefinition = "integer default 0")
    private int numPurchased = 0;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime postDate;



    /*
     * Constructors
     */

    public Offer(Seller seller, String title, boolean availability, String description, String offerImagePath, String type, int price, String payment, int numPurchased) {
        this.seller = seller;
        this.title = title;
        this.availability = availability;
        this.description = description;
        this.offerImagePath = offerImagePath;
        this.type = type;
        this.price = price;
        this.payment = payment;
        this.numPurchased = numPurchased;
    }

    public Offer(Long offerID, Seller seller, String title, boolean availability, String description, String offerImagePath, String type, int price, String payment, int numPurchased) {
        this.offerID = offerID;
        this.seller = seller;
        this.title = title;
        this.availability = availability;
        this.description = description;
        this.offerImagePath = offerImagePath;
        this.type = type;
        this.price = price;
        this.payment = payment;
        this.numPurchased = numPurchased;
    }
}
