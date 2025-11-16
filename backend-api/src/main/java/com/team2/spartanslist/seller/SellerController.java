package com.team2.spartanslist.seller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.team2.spartanslist.offer.Offer;
import com.team2.spartanslist.offer.OfferService;
import com.team2.spartanslist.order.Order;
import com.team2.spartanslist.order.OrderService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/sellers")
public class SellerController{
    @Autowired
    private final SellerService sellerService;
    @Autowired
    private final OfferService offerService;
    @Autowired
    private final OrderService orderService;

    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        Seller newSeller = new Seller();
        model.addAttribute("newSeller", newSeller);
        model.addAttribute("title", "Seller Registration");
        return "seller-registration-form";
    }

    /** endpoint to add a seller
     * @param seller the seller to be added
     * @param model
     * @return
     */
    @PostMapping
    public String createSeller(Model model, Seller newSeller) {
        String pageTitle = String.format("View %s's profile", newSeller.getUsername());
        model.addAttribute("seller", newSeller);
        model.addAttribute("title", pageTitle);
        sellerService.createSeller(newSeller);
        return "seller/seller-details";
    }

    /** endpoint to get all sellers
     * @param model
     * @return
     */
    @GetMapping
    public String getAllSellers(Model model){
        model.addAttribute("sellers", sellerService.getAllSellers());
        model.addAttribute("title", "View All Sellers");
        return "seller/sellers-list";
    }

    /** endpoint to delete a seller
     * @param model
     * @param sellerID the id of the seller to be deleted
     * @return
     */
    @GetMapping("/delete/{sellerID}")
    public Object deleteSeller(Model model, @PathVariable Long sellerID){
        model.addAttribute("sellers", sellerService.getAllSellers());
        model.addAttribute("title", "View All Sellers");
        return "sellers-list";
    }

    //  TODO : add a follow count by counting mail lists relations tied to seller

    /** endpoint to get a seller by id
     * @param model
     * @param sellerID id of the seller to get
     * @return
     */
    @GetMapping("/{sellerID}")
    public String getSellerById(Model model, @PathVariable Long sellerID){
        Seller seller = sellerService.getSellerById(sellerID);
        if (seller == null){
            return "redirect:/sellers?error=seller%20not%20found";
        }
        String pageTitle = String.format("View %s's profile",seller.getUsername());
        model.addAttribute("seller", seller);
        model.addAttribute("title", pageTitle);
        
        List<Offer> offers = offerService.findByAvailableAndSellerLimitThree(seller.getSellerID());
        model.addAttribute("offers", offers);
        return "seller/seller-details";
    }

    /** endpoint to find a seller by phone
     * note : honestly this should not be available to the users,
     *  but it creates a page regardless
     * @param model
     * @param userPhone
     * @return
     */
    @GetMapping("/seller/phone/{userPhone}")
    public Object getSellerByPhone(Model model, @PathVariable String userPhone){
        Seller seller = sellerService.getSellerByPhone(userPhone);
        if (seller == null){
            return "redirect:/sellers?error=seller%20not%20found";
        }
        String pageTitle = String.format("View %s's profile",seller.getUsername());
        model.addAttribute("seller", seller);
        model.addAttribute("title", pageTitle);
        List<Offer> offers = offerService.findBySeller(seller.getSellerID());
        model.addAttribute("offers", offers);
        return "seller/seller-details";
    }
    
    /** endpoint to update a seller
     * 
     * @param sellerID the id of the seller to be updated
     * @param nSeller the new details of the seller
     * @return
     */
    @PostMapping("/{sellerID}")
    public Object updateSeller(Model model, @PathVariable Long sellerID, Seller nSeller){
        sellerService.updateSeller(sellerID, nSeller);
        return "seller/seller-details";
    }

    /** endpoint to seller update form
     * @param model
     * @param sellerID
     * @return
     */
    @GetMapping("/updateForm/{sellerID}")
    public Object showSellerUpdateForm(Model model, @PathVariable long sellerID){
        return "seller/seller-update";
    }

    /** endpoint to see seller's homepage
     * @param model
     * 
     */
    @GetMapping("/home")
    public String showSellerHome(Model model) {
        Seller seller = sellerService.getSellerById(1L);

        String pageTitle = String.format("Welcome, %s.",seller.getUsername());
        model.addAttribute("seller", seller);
        model.addAttribute("title", pageTitle);
        
        List<Offer> offers = offerService.findBySeller(seller.getSellerID());
        model.addAttribute("offers", offers);

        List<Order> orders = orderService.getOrdersbySellerAndStatus(seller.getSellerID(), 1);
        model.addAttribute("requests", orders);
        return "seller/seller-home";
    }
}