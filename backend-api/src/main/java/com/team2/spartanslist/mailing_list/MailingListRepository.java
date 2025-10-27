package com.team2.spartanslist.mailing_list;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MailingListRepository extends JpaRepository<MailingList, Long>{
    @Query(value = "SELECT mailing_listID FROM mailing_list m WHERE m.shopperID = :shopperID AND m.sellerID = :sellerID", nativeQuery = true)
    public Long getMailingListID(@Param("shopperID") Long shopperID, @Param("sellerID") Long sellerID);

    @Query(value = "SELECT * FROM mailing_list m WHERE m.shopperID = :shopperID", nativeQuery = true)
    public List<MailingList> getAllSubs(@Param("shopperID") Long shopperID);

    @Query(value = "SELECT * FROM mailing_list m WHERE m.shopperID = :shopperID AND m.sellerID = :sellerID", nativeQuery = true)
    public List<MailingList> getSubscriptionStatus(@Param("shopperID") Long shopperID, @Param("sellerID") Long sellerID);
}
