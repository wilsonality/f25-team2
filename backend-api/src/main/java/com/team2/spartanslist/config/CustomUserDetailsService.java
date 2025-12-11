package com.team2.spartanslist.config;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.team2.spartanslist.seller.Seller;
import com.team2.spartanslist.seller.SellerService;
import com.team2.spartanslist.shopper.Shopper;
import com.team2.spartanslist.shopper.ShopperService;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private SellerService sellerService;
    @Autowired
    private ShopperService shopperService;
    
    @Override
    public UserDetails loadUserByUsername(String usernameOrPhone) throws UsernameNotFoundException {
        // try to find by phone or username
        try{
            System.out.println("looking for seller by phone");
            Seller seller = sellerService.getSellerByPhone(usernameOrPhone);
            if (seller == null) {
                System.out.println("looking for seller by username");
                seller = sellerService.getSellerByUsername(usernameOrPhone);
            }
            System.out.println("\n\nDEBUG ::: before null check, SELLER IS");
            System.out.println(seller);
            if (seller != null) {
                System.out.println("found seller with input" + usernameOrPhone);
                return User.builder()
                    .username(seller.getUserPhone()) 
                    .password(seller.getPassword())
                    .authorities(Collections.singletonList(new SimpleGrantedAuthority("ROLE_SELLER")))
                    .build();
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        

        System.out.println("looking for a shopper now");
        
        try{
            // try to find by phone or username
            System.out.println("looking for shopper by phone");
            Shopper shopper = shopperService.getShopperByPhone(usernameOrPhone);
            if (shopper == null) {
                System.out.println("looking for shopper by username");
                shopper = shopperService.getShopperByUsername(usernameOrPhone);
            }
            if (shopper != null) {
                System.out.println("found shopper with input" + usernameOrPhone);
                return User.builder()
                    .username(shopper.getUserPhone()) // Use phone as principal
                    .password(shopper.getPassword())
                    .authorities(Collections.singletonList(new SimpleGrantedAuthority("ROLE_SHOPPER")))
                    .build();
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        
        throw new UsernameNotFoundException("User not found: " + usernameOrPhone);
    }
}
