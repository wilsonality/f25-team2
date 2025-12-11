package com.team2.spartanslist.review;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
@Table(name = "Review")
@Data
@NoArgsConstructor
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reviewID;

    @JoinColumn(name = "shopperID",nullable = false)
    @ManyToOne
    private Shopper author;
    
    @ManyToOne
    @JsonIgnoreProperties("review")
    @JoinColumn(name = "offerID")
    private Offer offer;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime postDate;

    private String content;
    
    @Column(nullable = false)
    private int rating;

    private String reply;

    /*
     * Constructors
     */

    public Review(Long reviewID, Shopper author, Offer offer, String content, LocalDateTime postDate, int rating, String reply) {
        this.reviewID = reviewID;
        this.author = author;
        this.offer = offer;
        this.content = content;
        this.postDate = postDate;
        this.content = content;
        this.rating = rating;
        this.reply = reply;
    }

    public Review(Shopper author, Offer offer, String content, LocalDateTime postDate, int rating, String reply) {
        this.author = author;
        this.offer = offer;
        this.content = content;
        this.postDate = postDate;
        this.content = content;
        this.rating = rating;
        this.reply = reply;
    }
}