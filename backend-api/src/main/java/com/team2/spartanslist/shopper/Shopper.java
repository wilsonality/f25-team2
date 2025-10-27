package com.team2.spartanslist.shopper;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data  
@NoArgsConstructor
@Table(name = "Shopper")
public class Shopper {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long shopperID;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String userPhone;
    
    private String profileImage;
    private String profileBio;
}