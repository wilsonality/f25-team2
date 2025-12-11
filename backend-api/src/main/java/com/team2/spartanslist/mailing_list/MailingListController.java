package com.team2.spartanslist.mailing_list;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.team2.spartanslist.Global;
import com.team2.spartanslist.seller.SellerService;

@Controller
@RequestMapping("/mailinglist")
public class MailingListController {
    @Autowired
    private MailingListService mailingListService;
    @Autowired
    private SellerService sellerService;


    /**
     * { 
     *   sellerID: 
     *   shopperID:
     * }
     */
    @GetMapping("/subscribe/{shopperID}/{sellerID}")
    public MailingList subscribe(@PathVariable Long shopperID, @PathVariable Long sellerID ) {
        return mailingListService.subscribe(shopperID, sellerID);
    }

    @PostMapping("/unsubscribe/{shopperID}/{sellerID}")
    public String unsubscribe(@PathVariable Long shopperID, @PathVariable Long sellerID) {
        mailingListService.unsubscribe(shopperID, shopperID);
        return "redirect:/mailinglist/subscriptions/" + shopperID;
    }


    // Returns a list of all subscriptions for shopperID
    @GetMapping("/subscriptions/{shopperID}")
    public String getAllSubs(Model model, @PathVariable Long shopperID) {
        model.addAttribute("subs", mailingListService.getSubsByShopperID(shopperID));
        model.addAttribute("shopperID", shopperID);

        return "/shopper/shopper-mailing-list";
    }
}
