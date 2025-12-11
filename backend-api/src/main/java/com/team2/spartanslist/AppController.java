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
import com.team2.spartanslist.seller.Seller;
import com.team2.spartanslist.seller.SellerService;
import com.team2.spartanslist.shopper.Shopper;
import com.team2.spartanslist.shopper.ShopperService;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class AppController {
    private final OfferService offerService;
    private final SellerService sellerService;
    private final ShopperService shopperService;
    private final AuthenticationManager authenticationManager;

    @GetMapping({"", "/", "/home"})
    public String redirectToLanding(Model model, Authentication auth) {
        if (auth != null && auth.isAuthenticated()){
            // check for user role, then send to appropriate home
            if (auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_SELLER"))){
                return "redirect:/sellers/home";
            } else if (auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_SHOPPER"))){
                return "redirect:/shoppers/home";
            }
        }

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

    /** endpoint to login a seller */
    @PostMapping("/login/seller")
    public Object verifySeller(Model model, @RequestParam String username, @RequestParam String password, HttpServletRequest request, HttpServletResponse response){
        try {
            // create a token
            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password);
            Authentication auth = authenticationManager.authenticate(token);

            // check if user is a seller, not a shopper
            if (!auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_SELLER"))){
                return "redirect:/login?error";
            }

            SecurityContextHolder.getContext().setAuthentication(auth);
            
            // get details and create cookie using authenticated phone
            String authenticatedPhone = auth.getName();
            Seller seller = sellerService.getSellerByPhone(authenticatedPhone);
            
            Cookie userCookie = new Cookie("userType", "seller");
            userCookie.setMaxAge(7 * 24 * 60 * 60);
            userCookie.setPath("/");
            response.addCookie(userCookie);
            
            Cookie usernameCookie = new Cookie("username", seller.getUsername());
            usernameCookie.setMaxAge(7 * 24 * 60 * 60);
            usernameCookie.setPath("/");
            response.addCookie(usernameCookie);
            
            return "redirect:/sellers/home";

        } catch (BadCredentialsException e){
            return "redirect:/login?error";
        }
    }

    /** endpoint to login a shopper */
    @PostMapping("/login/shopper")
    public Object verifyShopper(Model model, @RequestParam String username, @RequestParam String password, HttpServletRequest request, HttpServletResponse response){
        try {
            // create a token
            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password);
            Authentication auth = authenticationManager.authenticate(token);

            // check if user is a shopper, not a seller
            if (!auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_SHOPPER"))){
                return "redirect:/login?error";
            }

            SecurityContextHolder.getContext().setAuthentication(auth);
            
            // get details and create cookie using authenticated phone
            String authenticatedPhone = auth.getName();
            Shopper shopper = shopperService.getShopperByPhone(authenticatedPhone);
            
            Cookie userCookie = new Cookie("userType", "shopper");
            userCookie.setMaxAge(7 * 24 * 60 * 60);
            userCookie.setPath("/");
            response.addCookie(userCookie);
            
            Cookie usernameCookie = new Cookie("username", shopper.getUsername());
            usernameCookie.setMaxAge(7 * 24 * 60 * 60);
            usernameCookie.setPath("/");
            response.addCookie(usernameCookie);
            
            return "redirect:/shoppers/home";

        } catch (BadCredentialsException e){
            return "redirect:/login?error";
        }
    }

    @GetMapping("/logout")
    public Object logout(Model model, HttpServletRequest request, HttpServletResponse response){
        // clear cookies
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                cookie.setValue("");
                cookie.setPath("/");
                cookie.setMaxAge(0);
                response.addCookie(cookie);
            }
        }

        // clear security context
        SecurityContextHolder.clearContext();

        return "redirect:/";
    }

    @GetMapping("/403")
    public Object accessDeniedPage(Model model){
        return "403";
    }
    
}
