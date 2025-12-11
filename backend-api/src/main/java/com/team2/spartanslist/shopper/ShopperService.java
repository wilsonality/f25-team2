package com.team2.spartanslist.shopper;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.team2.spartanslist.seller.Seller;

@Service
public class ShopperService {
    @Autowired 
    private ShopperRepository shopperRepository;
    @Autowired
    private org.springframework.security.crypto.password.PasswordEncoder passwordEncoder;

    private static final String UPLOAD_DIR = "backend-api/src/main/resources/static/shopper-pictures";
    private static final String SOURCE_DIR = "backend-api/target/main/resources/static/shopper-pictures";

    public List<Shopper> getAllShoppers() {
        return shopperRepository.findAll();
    }

    public Shopper getShopper(Long shopperID) {
        return shopperRepository.findById(shopperID).orElse(null);
    }

    public Shopper createShopper(Shopper shopper, MultipartFile shopperPicture) {
        // hash pass before saving
        shopper.setPassword(passwordEncoder.encode(shopper.getPassword()));

        Shopper newShopper = shopperRepository.save(shopper);

        if (shopperPicture != null && !shopperPicture.isEmpty()){
            String ogFileName = shopperPicture.getOriginalFilename();
            try{
                if (ogFileName != null && !ogFileName.isEmpty()){
                    // rename file to Shopper1.extension
                    String fileExtension = ogFileName.substring(ogFileName.lastIndexOf(".")+1);
                    String fileName = "Shopper" + String.valueOf(newShopper.getShopperID()) + "." + fileExtension;

                    // save to target directory for run time
                    Path targetPath = Paths.get(UPLOAD_DIR + fileName);
                    Files.createDirectories(targetPath.getParent());
                    Files.copy(shopperPicture.getInputStream(), targetPath, StandardCopyOption.REPLACE_EXISTING);

                    // save to source directory for persistence
                    Path sourcePath = Paths.get(SOURCE_DIR + fileName);
                    Files.createDirectories(sourcePath.getParent());
                    Files.copy(shopperPicture.getInputStream(), sourcePath, StandardCopyOption.REPLACE_EXISTING);

                    /** now it's saved, so update
                     * newseller to have this profile picture
                     * we use webpath for convenience
                     * now imgs just use src="seller.profileimgpath()"
                    */
                    String webPath = "/shopper-pictures/" + fileName;
                    newShopper.setProfileImagePath(webPath);
                }
            } catch (Exception e){
                e.printStackTrace();
            }
        }
        
        return newShopper;
    }

    public Shopper updateShopper(Long shopperID, Shopper nShopper, MultipartFile shopperPicture) {
        Shopper existingShoppper = shopperRepository.findById(shopperID).orElseThrow();

        existingShoppper.setUsername(nShopper.getUsername());
        existingShoppper.setUserPhone(nShopper.getUserPhone());
        existingShoppper.setProfileBio(nShopper.getProfileBio());

        if (shopperPicture != null && !shopperPicture.isEmpty()){
            String ogFileName = shopperPicture.getOriginalFilename();
            try{
                if (ogFileName != null && !ogFileName.isEmpty()){
                    // rename file to Shopper1.extension
                    String fileExtension = ogFileName.substring(ogFileName.lastIndexOf(".")+1);
                    String fileName = "Shopper" + String.valueOf(existingShoppper.getShopperID()) + "." + fileExtension;

                    // save to target directory for run time
                    Path targetPath = Paths.get(UPLOAD_DIR + fileName);
                    Files.createDirectories(targetPath.getParent());
                    Files.copy(shopperPicture.getInputStream(), targetPath, StandardCopyOption.REPLACE_EXISTING);

                    // save to source directory for persistence
                    Path sourcePath = Paths.get(SOURCE_DIR + fileName);
                    Files.createDirectories(sourcePath.getParent());
                    Files.copy(shopperPicture.getInputStream(), sourcePath, StandardCopyOption.REPLACE_EXISTING);

                    /** now it's saved, so update
                     * newseller to have this profile picture
                     * we use webpath for convenience
                     * now imgs just use src="seller.profileimgpath()"
                    */
                    String webPath = "/shopper-pictures/" + fileName;
                    existingShoppper.setProfileImagePath(webPath);
                }
            } catch (Exception e){
                e.printStackTrace();
            }
        }

        return shopperRepository.save(existingShoppper);
    }

    public Shopper getShopperByPhone(String userPhone){
        return shopperRepository.findByUserPhone(userPhone);
    }

    public Shopper getShopperByUsername(String username){
        return shopperRepository.findByUsername(username);
    }
}
