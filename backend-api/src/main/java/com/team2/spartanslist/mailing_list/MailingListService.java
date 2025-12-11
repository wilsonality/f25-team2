package com.team2.spartanslist.mailing_list;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.team2.spartanslist.seller.Seller;
import com.team2.spartanslist.seller.SellerService;
import com.team2.spartanslist.shopper.Shopper;
import com.team2.spartanslist.shopper.ShopperService;

@Service
public class MailingListService {
    @Autowired
    private MailingListRepository mailingListRepository;
    private SellerService sellerService;
    private ShopperService shopperService;
    
    /** method to create a mailing list 
     * @param mailingList mailing list to create
     * note : this Ml contains a seller and shopper that only has an ID defined.
     * we use this id to get the seller and shopper object and set it to the Ml
     * @return
    */
    public MailingList subscribe(Long shopperID, Long sellerID) {
        MailingList mailingList = new MailingList();
        mailingList.setShopperID(shopperID);
        mailingList.setSellerID(sellerID);

        return mailingListRepository.save(mailingList);
    }

    public void unsubscribe(Long shopperID, Long sellerID) {
        Long mailingListID = mailingListRepository.findMailingListID(shopperID, shopperID);
        mailingListRepository.deleteById(mailingListID);
    }

    public List<MailingList> getSubsByShopperID(Long shopperID) {
        return mailingListRepository.findSubsByShopperID(shopperID);
    }

    public boolean isSubscribed(Long shopperID, Long sellerID) {
        List<MailingList> subs = mailingListRepository.findSubsByShopperIDAndSellerID(shopperID, sellerID);

        if (!subs.isEmpty()) {
            return true; 
        }

        return false;
    }
}
