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
        // Try to find as Seller first
        Seller seller = sellerService.getSellerByPhone(usernameOrPhone);
        if (seller != null) {
            return User.builder()
                .username(seller.getUsername())
                .password(seller.getPassword())
                .authorities(Collections.singletonList(new SimpleGrantedAuthority("ROLE_SELLER")))
                .build();
        }
        
        // Try to find as Shopper
        Shopper shopper = shopperService.getShopperByPhone(usernameOrPhone);
        if (shopper != null) {
            return User.builder()
                .username(shopper.getUsername())
                .password(shopper.getPassword())
                .authorities(Collections.singletonList(new SimpleGrantedAuthority("ROLE_SHOPPER")))
                .build();
        }
        
        throw new UsernameNotFoundException("User not found by username or phone: " + usernameOrPhone);
    }
}
