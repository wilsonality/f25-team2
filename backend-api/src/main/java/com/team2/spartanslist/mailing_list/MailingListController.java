package com.team2.spartanslist.mailing_list;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MailingListController {
    @Autowired
    private MailingListService mailingListService;
    
    // Have a Shopper subscribe to a Seller
    @PostMapping("/subscribe")
    public void subscribe(@RequestBody MailingList newMailingList) {
        mailingListService.subscribe(newMailingList);
    }
}
