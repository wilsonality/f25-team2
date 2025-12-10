package com.team2.spartanslist;

import java.util.List;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.team2.spartanslist.offer.Offer;
import com.team2.spartanslist.offer.OfferService;
import com.team2.spartanslist.seller.SellerService;
import com.team2.spartanslist.shopper.ShopperService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class AppController {
    private final OfferService offerService;
    private final SellerService sellerService;
    private final ShopperService shopperService;
    private final AuthenticationManager authenticationManager;

    @GetMapping({"", "/", "/home"})
    public String redirectToLanding(Model model) {
        model.addAttribute("title", "Welcome to Spartan's List");
        List<Offer> offers = offerService.findByAvailability(true);
        model.addAttribute("offers", offers);
        return "home";
    }

    @GetMapping("/register")
    public Object showRegister(Model model){
        model.addAttribute("title", "Register for Spartan's List");
        return "register";
    }

    @GetMapping("/login")
    public Object showLogin(Model model){
        model.addAttribute("title", "Sign In to Spartan's List");
        return "log-in";
    }

    /** endpoint to login a user */
    @PostMapping("/login/seller")
    public Object verifySeller(Model model, @RequestParam String username, @RequestParam String password, HttpServletRequest request){
        try {
            // create a token
            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password);
            Authentication auth = authenticationManager.authenticate(token);

            // check if user is a seller, not a shopper
            if (!auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_SELLER"))){
                return "redirect:/login?error";
            }

            SecurityContextHolder.getContext().setAuthentication(auth);
            return "redirect:/seller/home";

        } catch (BadCredentialsException e){
            return "redirect:/login?error";
        }
    }

    @PostMapping("/login/shopper")
    public Object verifyShopper(Model model, @RequestParam String username, @RequestParam String password, HttpServletRequest request){
        try {
            // create a token
            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password);
            Authentication auth = authenticationManager.authenticate(token);

            // check if user is a seller, not a shopper
            if (!auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_SHOPPER"))){
                return "redirect:/login?error";
            }

            SecurityContextHolder.getContext().setAuthentication(auth);
            return "redirect:/shopper/home";

        } catch (BadCredentialsException e){
            return "redirect:/login?error";
        }
    }

    @GetMapping("/403")
    public Object accessDeniedPage(Model model){
        return "403";
    }
    
}
