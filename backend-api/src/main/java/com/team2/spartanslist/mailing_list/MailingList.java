package com.team2.spartanslist.mailing_list;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "mailing_list")
public class MailingList {
    @Id
    @Column(nullable = false)
    private Long mailerID;

    @Column(nullable = false)
    private Long shopperID;
}
