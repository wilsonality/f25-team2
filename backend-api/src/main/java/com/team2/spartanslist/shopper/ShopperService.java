package com.team2.spartanslist.shopper;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public Shopper updateShopper(Long shopperID, Shopper updatedShopper) {
        shopperRepository.save(updatedShopper);
        return getShopper(shopperID);
    }
}
