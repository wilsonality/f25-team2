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
    private Long user_ID;

    @Column(nullable = false)
    private String user_name;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String user_phone;
    
    private String profile_image;
    private String profile_bio;

    @OneToMany(mappedBy = "seller")
    @JsonIgnoreProperties("seller")
    private List<Offer> offers = new ArrayList<>();


    /*
     * Constructors
     */

    public Seller(Long user_ID, String user_name, String password, String user_phone, String profile_image, String profile_bio) {
        this.user_ID = user_ID;
        this.user_name = user_name;
        this.user_phone = user_phone;
        this.password = password;
        this.profile_image = profile_image;
        this.profile_bio = profile_bio;
    }
    public Seller (String user_name, String password, String user_phone, String profile_image, String profile_bio) {
        this.user_name = user_name;
        this.user_phone = user_phone;
        this.password = password;
        this.profile_image = profile_image;
        this.profile_bio = profile_bio;
    }
}