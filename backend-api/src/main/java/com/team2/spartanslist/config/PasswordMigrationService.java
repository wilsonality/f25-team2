package com.team2.spartanslist.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.team2.spartanslist.seller.Seller;
import com.team2.spartanslist.seller.SellerRepository;
import com.team2.spartanslist.shopper.Shopper;
import com.team2.spartanslist.shopper.ShopperRepository;

import jakarta.annotation.PostConstruct;

@Service
public class PasswordMigrationService {
    @Autowired
    private SellerRepository sellerRepository;
    
    @Autowired
    private ShopperRepository shopperRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @PostConstruct // Runs once when app starts
    public void migratePasswords() {
        // Migrate seller passwords
        List<Seller> sellers = sellerRepository.findAll();
        for (Seller seller : sellers) {
            String password = seller.getPassword();
            // Check if already hashed (BCrypt hashes start with $2a$, $2b$, or $2y$)
            if (!password.startsWith("$2")) {
                seller.setPassword(passwordEncoder.encode(password));
                sellerRepository.save(seller);
            }
        }
        
        // Migrate shopper passwords
        List<Shopper> shoppers = shopperRepository.findAll();
        for (Shopper shopper : shoppers) {
            String password = shopper.getPassword();
            if (!password.startsWith("$2")) {
                shopper.setPassword(passwordEncoder.encode(password));
                shopperRepository.save(shopper);
            }
        }
    }
}
