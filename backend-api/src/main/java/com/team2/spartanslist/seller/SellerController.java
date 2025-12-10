package com.team2.spartanslist.seller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

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
        return "seller/seller-registration-form";
    }

    /** endpoint to add a seller
     * @param seller the seller to be added
     * @param model
     * @return
     */
    @PostMapping
    public String createSeller(Model model, Seller newSeller, @RequestParam(required = false)MultipartFile sellerPicture) {
        Seller seller = sellerService.createSeller(newSeller, sellerPicture);
        // check for unique phone
        Seller check = sellerService.getSellerByPhone(newSeller.getUserPhone());
        if (check == null){
            return "redirect:/sellers/register?error=failed%20to%20create%20seller%20account";
        }
        return "redirect:/sellers/" + seller.getSellerID(); 
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
     * i'm redirecting to the getbyID url to simplify
     * @param model
     * @param userPhone
     * @return
     */
    @GetMapping("/seller/phone/{userPhone}")
    public Object getSellerByPhone(Model model, @PathVariable String userPhone){
        Seller seller = sellerService.getSellerByPhone(userPhone);
        return "redirect:/sellers/" + seller.getSellerID();
    }
    
    /** endpoint to update a seller
     * 
     * @param sellerID the id of the seller to be updated
     * @param nSeller the new details of the seller
     * @return
     */
    @PostMapping("/{sellerID}")
    public String updateSeller(@PathVariable Long sellerID, Seller nSeller, @RequestParam(required = false)MultipartFile sellerPicture, Model model){
        System.out.println("Updating seller " + sellerID + " with data: " + nSeller);
        sellerService.updateSeller(sellerID, nSeller, sellerPicture);
        return "redirect:/sellers/" + sellerID;
    }

    /** endpoint to seller update form
     * @param model
     * @param sellerID
     * @return
     */
    @GetMapping("/updateForm/{sellerID}")
    public Object showSellerUpdateForm(Model model, @PathVariable long sellerID){
        Seller seller = sellerService.getSellerById(sellerID);
        String pageTitle = String.format("Edit Profile For %s.",seller.getUsername());
        model.addAttribute("title", pageTitle);
        model.addAttribute("seller", seller);
        return "seller/seller-update";
    }

    /** endpoint to see seller's homepage
     * @param model
     * @return
     */
    @GetMapping("/home")
    public Object showSellerHome(Model model) {
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