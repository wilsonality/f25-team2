package com.team2.spartanslist.cart;

import java.util.ArrayList;
import java.util.List;

import com.team2.spartanslist.offer.Offer;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "cart")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cartItemID;

    @OneToOne
    @JoinColumn(name = "shopperID", nullable = false)
    private Long shopperID;

    @ManyToMany
    @JoinColumn(name = "offerID", nullable = false)
    private Long offerID;
}