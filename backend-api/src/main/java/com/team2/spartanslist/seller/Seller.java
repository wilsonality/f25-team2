package com.team2.spartanslist.seller;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.team2.spartanslist.offer.Offer;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Seller")
@Data
@NoArgsConstructor
public class Seller {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userID;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String user_phone;
    
    private String profileImage;
    private String profileBio;

    @OneToMany(mappedBy = "seller")
    @JsonIgnoreProperties("seller")
    private List<Offer> offers = new ArrayList<>();


    /*
     * Constructors
     */

    public Seller(Long user_ID, String username, String password, String user_phone, String profileImage, String profileBio) {
        this.userID = user_ID;
        this.username = username;
        this.user_phone = user_phone;
        this.password = password;
        this.profileImage = profileImage;
        this.profileBio = profileBio;
    }
    public Seller (String username, String password, String user_phone, String profileImage, String profileBio) {
        this.username = username;
        this.user_phone = user_phone;
        this.password = password;
        this.profileImage = profileImage;
        this.profileBio = profileBio;
    }
}