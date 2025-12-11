package com.team2.spartanslist.mailing_list;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MailingListRepository extends JpaRepository<MailingList, Long>{
    @Query(value = "SELECT mailing_listID FROM mailing_list m WHERE m.shopperID = ?1 AND m.sellerID = ?2", nativeQuery = true)
    public Long findMailingListID(Long shopperID, Long sellerID);

    @Query(value = "SELECT * FROM mailing_list m WHERE m.shopperID = ?1", nativeQuery = true)
    public List<MailingList> findSubsByShopperID(Long shopperID);

    @Query(value = "SELECT * FROM mailing_list m WHERE m.shopperID = ?1 AND m.sellerID = ?2", nativeQuery = true)
    public List<MailingList> findSubsByShopperIDAndSellerID(Long shopperID, Long sellerID);
}
