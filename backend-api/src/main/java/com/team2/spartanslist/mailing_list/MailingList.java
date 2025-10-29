package com.team2.spartanslist.mailing_list;

import com.team2.spartanslist.seller.Seller;
import com.team2.spartanslist.shopper.Shopper;

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
@Data
@NoArgsConstructor
@Table(name = "mailing_list")
public class MailingList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mailingListID;

    @ManyToOne
    @JoinColumn(name="sellerID",nullable = false)
    private Seller seller;

    @ManyToOne
    @JoinColumn(name="shopperID",nullable = false)
    private Shopper shopper;
}
