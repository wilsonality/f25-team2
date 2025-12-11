package com.team2.spartanslist.seller;

import java.time.LocalDate;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
    private Long sellerID;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String userPhone;
    
    private String profileImagePath;
    
    private String profileBio;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDate joinDate;


    /*
     * Constructors
     */

    public Seller(Long sellerID, String username, String password, String userPhone, String profileImagePath, String profileBio) {
        this.sellerID = sellerID;
        this.username = username;
        this.userPhone = userPhone;
        this.password = password;
        this.profileImagePath = profileImagePath;
        this.profileBio = profileBio;
    }
    public Seller (String username, String password, String userPhone, String profileImagePath, String profileBio) {
        this.username = username;
        this.userPhone = userPhone;
        this.password = password;
        this.profileImagePath = profileImagePath;
        this.profileBio = profileBio;
    }
}