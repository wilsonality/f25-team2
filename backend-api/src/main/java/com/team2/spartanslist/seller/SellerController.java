package com.team2.spartanslist.seller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/sellers")
public class SellerController{
    private final SellerService sellerService;

    /** endpoint to add a seller
     * 
     * @param seller the seller to be added
     * @return
     */
    @PostMapping
    public ResponseEntity<Seller> createSeller(@Valid @RequestBody Seller seller){
        return ResponseEntity.ok(sellerService.createSeller(seller));
    }

    /** endpoint to get all sellers
     *
     * @return
     */
    @GetMapping
    public ResponseEntity<List<Seller>> getAllSellers(){
        return ResponseEntity.ok(sellerService.getAllSellers());
    }

    /** endpoint to delete a seller
     *
     * @param sellerID the id of the seller to be deleted
     * @return
     */
    @DeleteMapping("/{sellerID}")
    public ResponseEntity<List<Seller>> deleteSeller(@PathVariable Long sellerID){
        return ResponseEntity.ok(sellerService.deleteSeller(sellerID));
    }

    /** endpoint to get all offers of a seller
     *
     * @param sellerID the id of the seller to get offers for
     * @return
     */

    /** endpoint to get a seller by id
     * 
     * @param sellerID id of the seller to get
     * @return
     */
    @GetMapping("/{sellerID}")
    public ResponseEntity<Seller> getSellerById(@PathVariable Long sellerID){
        return ResponseEntity.ok(sellerService.getSellerById(sellerID));
    }

    @GetMapping("/seller/{userPhone}")
    public ResponseEntity<Seller> getSellerByPhone(@PathVariable String userPhone){
        return ResponseEntity.ok(sellerService.getSellerByPhone(userPhone));
    }
    
    /** endpoint to update a seller
     * 
     * @param sellerID the id of the seller to be updated
     * @param nSeller the new details of the seller
     * @return
     */
    @PutMapping("/{sellerID}")
    public ResponseEntity<Seller> updateSeller(@PathVariable Long sellerID, @Valid @RequestBody Seller nSeller){
        return ResponseEntity.ok(sellerService.updateSeller(sellerID, nSeller));
    }
}