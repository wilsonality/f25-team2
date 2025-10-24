package com.team2.spartanslist.mailing_list;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MailingListController {
    @Autowired
    private MailingListService mailingListService;
    /*
     * Post
     */
    

    // Shopper subscribes to seller
    @PostMapping("/subscribe")
    public void subscribe(@RequestParam Long shopperID, @RequestParam Long sellerID) {
    }
}
