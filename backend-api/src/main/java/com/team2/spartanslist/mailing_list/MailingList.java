package com.team2.spartanslist.mailing_list;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
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

    @JoinColumn(name="sellerID",nullable = false)
    private Long sellerID;

    @JoinColumn(name="shopperID",nullable = false)
    private Long shopperID;
}
