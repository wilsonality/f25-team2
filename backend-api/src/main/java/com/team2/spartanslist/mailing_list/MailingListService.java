package com.team2.spartanslist.mailing_list;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MailingListService {
    @Autowired
    private MailingListRepository mailingListRepository;

    public MailingList subscribe(MailingList mailingList) {
        return mailingListRepository.save(mailingList);
    }

    public void unsubscribe(Long shopperID, Long sellerID) {
        Long mailingListID = mailingListRepository.getMailingListID(shopperID, shopperID);
        mailingListRepository.deleteById(mailingListID);
    }

    public List<MailingList> getAllSubs(Long shopperID) {
        return mailingListRepository.getAllSubs(shopperID);
    }

    public List<MailingList> getSubscriptionStatus(Long shopperID, Long sellerID) {
        return mailingListRepository.getSubscriptionStatus(shopperID, sellerID);
    }
}
