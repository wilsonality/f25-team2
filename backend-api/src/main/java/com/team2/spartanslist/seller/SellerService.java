package com.team2.spartanslist.seller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class SellerService {
    @Autowired
    private final SellerRepository sellerRepository;

    public Seller createSeller(Seller seller){
        if (sellerRepository.findByUserPhone(seller.getUserPhone()) != null){
            throw new IllegalStateException("Phone number already registered.");
        }
        return sellerRepository.save(seller);
    }

    public Seller updateSeller(Long id, Seller nSeller){
        Seller seller = sellerRepository.findById(id).orElseThrow();

        seller.setUsername(nSeller.getUsername());
        seller.setUserPhone(nSeller.getUserPhone());
        seller.setProfileImage(nSeller.getProfileImage());
        seller.setProfileBio(nSeller.getProfileBio());

        return sellerRepository.save(seller);
    }

    public List<Seller> deleteSeller(Long sellerID){
        sellerRepository.deleteById(sellerID);
        return sellerRepository.findAll();
    }

    public Seller getSellerById(Long sellerID){
        return sellerRepository.findById(sellerID).orElseThrow(() -> new IllegalStateException("Seller with ID:" + sellerID + " does not exist."));
    }
    
    public Seller getSellerByPhone(String userPhone){
        return sellerRepository.findByUserPhone(userPhone);
    }

    public List<Seller> getAllSellers(){
        return sellerRepository.findAll();
    }
}
