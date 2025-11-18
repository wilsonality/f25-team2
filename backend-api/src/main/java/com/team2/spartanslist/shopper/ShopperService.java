package com.team2.spartanslist.shopper;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.team2.spartanslist.seller.Seller;

@Service
public class ShopperService {
    @Autowired 
    private ShopperRepository shopperRepository;

    public List<Shopper> getAllShoppers() {
    return shopperRepository.findAll();
    }

    public Shopper getShopper(Long shopperID) {
        return shopperRepository.findById(shopperID).orElse(null);
    }

    public Shopper createShopper(Shopper newShopper) {
        return shopperRepository.save(newShopper);
    }

    public Shopper updateShopper(Long shopperToUpdateID, Shopper updatedShopper) {
        Shopper shopperToUpdate = shopperRepository.findById(shopperToUpdateID).orElseThrow();

        shopperToUpdate.setUsername(updatedShopper.getUsername());
        shopperToUpdate.setUserPhone(updatedShopper.getUserPhone());
        shopperToUpdate.setProfileImage(updatedShopper.getProfileImage());
        shopperToUpdate.setProfileBio(updatedShopper.getProfileBio());

        return shopperRepository.save(shopperToUpdate);
    }

    public Shopper getShopperByUsername(String username) {
        return shopperRepository.findByUsername(username);
    }
}
