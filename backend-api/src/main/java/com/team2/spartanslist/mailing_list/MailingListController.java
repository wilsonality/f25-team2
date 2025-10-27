package com.team2.spartanslist.mailing_list;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MailingListController {
    @Autowired
    private MailingListService mailingListService;

    @PostMapping("/subscribe")
    public MailingList subscribe(@RequestBody MailingList mailingList ) {
        return mailingListService.subscribe(mailingList);
    }

    @DeleteMapping("/{shopperID}/unsubscribe/{sellerID}")
    public void unsubscribe(@PathVariable Long shopperID, @PathVariable Long sellerID) {
        mailingListService.unsubscribe(shopperID, shopperID);
    }

    @GetMapping("/{shopperID}/subscriptions")
    public List<MailingList> getAllSubs(@PathVariable Long shopperID) {
        return mailingListService.getAllSubs(shopperID);
    }

    @GetMapping("/{shopperID}/subscriptions/{sellerID}")
    public List<MailingList> getSubscriptionStatus(@PathVariable Long shopperID, @PathVariable Long sellerID) {
        return mailingListService.getSubscriptionStatus(shopperID, sellerID);
    }
}
