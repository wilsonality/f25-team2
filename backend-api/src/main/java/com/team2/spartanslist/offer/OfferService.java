package com.team2.spartanslist.offer;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.team2.spartanslist.seller.Seller;
import com.team2.spartanslist.seller.SellerService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class OfferService {
    @Autowired  
    private final OfferRepository offerRepository;
    private final SellerService sellerService;

    private static final String UPLOAD_DIR = "backend-api/src/main/resources/static/offer-pictures";
    private static final String SOURCE_DIR = "backend-api/target/main/resources/static/offer-pictures";

    /** method to create an offer
     * @param offer the offer to create
     * note : this offer contains a seller that only has an ID defined.
     * we use this id to get the seller object and set it to the offer
     * @return
     */
    
    public Offer createOffer(Offer offer, MultipartFile offerPicture){
        // save to db with no picture yet
        Offer newOffer = offerRepository.save(offer);

        if (offerPicture != null && !offerPicture.isEmpty()){
            String ogFileName = offerPicture.getOriginalFilename();
            try{
                if (ogFileName != null && !ogFileName.isEmpty()){
                    // rename file to Offer1.extension
                    String fileExtension = ogFileName.substring(ogFileName.lastIndexOf(".") + 1);
                    String fileName = "Offer" + String.valueOf(newOffer.getOfferID()) + "." + fileExtension;

                    // save to target directory for run time
                    Path targetPath = Paths.get(UPLOAD_DIR + fileName);
                    Files.createDirectories(targetPath.getParent());
                    Files.copy(offerPicture.getInputStream(), targetPath, StandardCopyOption.REPLACE_EXISTING);

                    // save to source directory for persistence
                    Path sourcePath = Paths.get(SOURCE_DIR + fileName);
                    Files.createDirectories(sourcePath.getParent());
                    Files.copy(offerPicture.getInputStream(), sourcePath, StandardCopyOption.REPLACE_EXISTING);
                    
                    /** now it's saved, so update
                     * newoffer to have this profile picture
                     * we use webpath for convenience
                     * now imgs just use src="offer.offerimagePath()"
                    */

                    String webPath = "/offer-pictures/" + fileName;

                    newOffer.setOfferImagePath(webPath);

                }
            } catch (Exception e){
                e.printStackTrace();
            }
        }
        

        return offerRepository.save(offer);
    }

    /** Method to update a given offer
     * 
     * @param offerID id of the offer we are updating
     * @param nOffer new offer object with user's update
     * @return saves updated offer to the repository
     */

    public Offer updateOffer(Long offerID, Offer nOffer, MultipartFile offerPicture){
        Offer existingOffer = offerRepository.findById(offerID).orElseThrow(() -> new IllegalStateException("Offer with ID:" + offerID + " could not be found."));

        existingOffer.setTitle(nOffer.getTitle());
        existingOffer.setDescription(nOffer.getDescription());
        existingOffer.setAvailability(nOffer.isAvailability());
        existingOffer.setPrice(nOffer.getPrice());
        existingOffer.setPayment(nOffer.getPayment());

        if (offerPicture != null && !offerPicture.isEmpty()){
            String ogFileName = offerPicture.getOriginalFilename();
            try{
                if (ogFileName != null && !ogFileName.isEmpty()){
                    // rename file to Offer1.extension
                    String fileExtension = ogFileName.substring(ogFileName.lastIndexOf(".") + 1);
                    String fileName = "Offer" + String.valueOf(existingOffer.getOfferID()) + "." + fileExtension;

                    // save to target directory for run time
                    Path targetPath = Paths.get(UPLOAD_DIR + fileName);
                    Files.createDirectories(targetPath.getParent());
                    Files.copy(offerPicture.getInputStream(), targetPath, StandardCopyOption.REPLACE_EXISTING);

                    // save to source directory for persistence
                    Path sourcePath = Paths.get(SOURCE_DIR + fileName);
                    Files.createDirectories(sourcePath.getParent());
                    Files.copy(offerPicture.getInputStream(), sourcePath, StandardCopyOption.REPLACE_EXISTING);
                    
                    /** now it's saved, so update
                     * newoffer to have this profile picture
                     * we use webpath for convenience
                     * now imgs just use src="offer.offerimagePath()"
                    */

                    String webPath = "/offer-pictures/" + fileName;

                    existingOffer.setOfferImagePath(webPath);

                }
            } catch (Exception e){
                e.printStackTrace();
            }
        }

        

        return offerRepository.save(existingOffer);
    }
    
    /** method to get an offer by its id
     * 
     * @param offerID id of the offer to get
     * @return
     */
    public Offer getOfferById(Long offerID) {
        return offerRepository.findById(offerID).orElseThrow(() -> new IllegalStateException("Seller with ID:" + offerID + " could not be found."));
    }

    /** method to delete an offer by its id
     *
     * @param offerID id of the offer to delete
     */
    public void deleteOffer(Long offerID) {
        offerRepository.deleteById(offerID);
    }

    /** method to get all offers
     *
     * @return all offers
     */
    public List<Offer> getAllOffers() {
        List<Offer> offers = offerRepository.findAll();
        System.out.println("getAllOffers() returned " + offers.size() + " offers");
        return offers;
    }

    /** method to see all available offers
     * @param availability availability of the offers
     * @return
     */
    public List<Offer> findByAvailability(boolean availability){
        return offerRepository.findByAvailability(availability);
    }

    /** method to get available offers of a seller
     * @param availability availability of the offer
     * @param sellerID id of the seller
     * @return 
     */
    public List<Offer> findByAvailabilityAndSeller(boolean availability, Long sellerID){
        Seller seller = sellerService.getSellerById(sellerID);
        return offerRepository.findByAvailabilityAndSeller(availability, seller);
    }

    /** method to get offers of a seller
     * @param sellerID the id of the seller
     * @return 
     */
    public List<Offer> findBySeller(Long sellerID){
        Seller seller = sellerService.getSellerById(sellerID);
        return offerRepository.findBySeller(seller);
    }

    /** method to get three available offers of a seller 
     * @param sellerID the id of the seller
     * @return
    */
    public List<Offer> findByAvailableAndSellerLimitThree(Long sellerID){
        Seller seller = sellerService.getSellerById(sellerID);
        return offerRepository.findTop3ByAvailabilityTrueAndSeller( seller);
    }

    /** method to get offers of a type
     * @param type type of the offer ("item" or "service")
     * @return
     */
    public List<Offer> findByType(String type){
        return offerRepository.findByType(type);
    }
}