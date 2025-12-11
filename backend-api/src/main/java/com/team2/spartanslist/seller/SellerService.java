package com.team2.spartanslist.seller;

import java.util.List;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class SellerService {
    @Autowired
    private final SellerRepository sellerRepository;
    @Autowired
    private org.springframework.security.crypto.password.PasswordEncoder passwordEncoder;

    private static final String UPLOAD_DIR = "backend-api/src/main/resources/static/seller-pictures";
    private static final String SOURCE_DIR = "backend-api/target/main/resources/static/seller-pictures";


    /** method to add a new seller
     * we encode the password before sending to repository
     * @param seller
     * @param sellerPicture profile picture for the seller
     * @return
     */
    public Seller createSeller(Seller seller, MultipartFile sellerPicture){
        if (sellerRepository.findByUserPhone(seller.getUserPhone()) != null){
            throw new IllegalStateException("Phone number already registered.");
        }
        
        // hash pass before saving
        seller.setPassword(passwordEncoder.encode(seller.getPassword()));

        // save to db with no picture yet
        Seller newSeller = sellerRepository.save(seller); 
        
        if (sellerPicture != null && !sellerPicture.isEmpty()){
            String ogFileName = sellerPicture.getOriginalFilename();
            try{
                if (ogFileName != null && !ogFileName.isEmpty()){
                    // rename the file to Seller1.extension
                    String fileExtension = ogFileName.substring(ogFileName.lastIndexOf(".")+1);
                    String fileName = "Seller" + String.valueOf(newSeller.getSellerID()) + "." + fileExtension;

                    // save to target directory for run time
                    Path targetPath = Paths.get(UPLOAD_DIR + fileName);
                    Files.createDirectories(targetPath.getParent());
                    Files.copy(sellerPicture.getInputStream(), targetPath, StandardCopyOption.REPLACE_EXISTING);

                    // save to source directory for persistence
                    Path sourcePath = Paths.get(SOURCE_DIR + fileName);
                    Files.createDirectories(sourcePath.getParent());
                    Files.copy(sellerPicture.getInputStream(), sourcePath, StandardCopyOption.REPLACE_EXISTING);

                    /** now it's saved, so update
                     * newseller to have this profile picture
                     * we use webpath for convenience
                     * now imgs just use src="seller.profileimgpath()"
                    */
                    String webPath = "/seller-pictures/" + fileName;
                    
                    newSeller.setProfileImagePath(webPath);
                }
            } catch (Exception e){
                e.printStackTrace();
            }
        }
        
        return newSeller;
    }

    public Seller updateSeller(Long sellerID, Seller nSeller, MultipartFile sellerPicture){
        Seller existingSeller = sellerRepository.findById(sellerID).orElseThrow();
        
        existingSeller.setUsername(nSeller.getUsername());
        existingSeller.setUserPhone(nSeller.getUserPhone());
        existingSeller.setProfileBio(nSeller.getProfileBio());
        // Keep existing password

        if (sellerPicture != null && !sellerPicture.isEmpty()){
            String ogFileName = sellerPicture.getOriginalFilename();
            try{
                if (ogFileName != null && !ogFileName.isEmpty()){
                    // rename the file to Seller1.extension
                    String fileExtension = ogFileName.substring(ogFileName.lastIndexOf(".")+1);
                    String fileName = "Seller" + String.valueOf(existingSeller.getSellerID()) + "." + fileExtension;

                    // save to the directory for run time
                    Path targetPath = Paths.get(UPLOAD_DIR + fileName);
                    Files.createDirectories(targetPath.getParent());
                    Files.copy(sellerPicture.getInputStream(), targetPath, StandardCopyOption.REPLACE_EXISTING);

                    // save to source directory for persistence
                    Path sourcePath = Paths.get(SOURCE_DIR + fileName);
                    Files.createDirectories(sourcePath.getParent());
                    Files.copy(sellerPicture.getInputStream(), sourcePath, StandardCopyOption.REPLACE_EXISTING);

                    /** now it's saved, so update
                     * existingseller to have this profile picture
                     * we use webpath for convenience
                     * now imgs just use src="seller.profileimgpath()"
                    */
                    String webPath = "/seller-pictures/" + fileName;

                    existingSeller.setProfileImagePath(webPath);
                }
            } catch (Exception e){
                e.printStackTrace();
            }

        }
    
        return sellerRepository.save(existingSeller);
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

    public Seller getSellerByUsername(String username) {
        return sellerRepository.findByUsername(username);
    }
}
