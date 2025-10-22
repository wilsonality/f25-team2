package com.team2.spartanslist.shopper;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Shopper")
public class Shopper {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long user_ID;
    private String user_name;
    private String password;
    private String user_phone;
    private String profile_image;
    private String profile_bio;

    /*
     * Constructors
     */

    public Shopper() {
    }

    public Shopper(Long user_ID, String user_name, String password, String user_phone, String profile_image, String profile_bio) {
        this.user_ID = user_ID;
        this.user_name = user_name;
        this.password = password;
        this.user_phone = user_phone;
        this.profile_image = profile_image;
        this.profile_bio = profile_bio;
    }

    /*
     * Getters and Setters
     */

    public Long getUser_ID() {
        return user_ID;
    }

    public void setUser_ID(Long user_ID) {
        this.user_ID = user_ID;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUser_phone() {
        return user_phone;
    }

    public void setUser_phone(String user_phone) {
        this.user_phone = user_phone;
    }

    public String getProfile_image() {
        return profile_image;
    }

    public void setProfile_image(String profile_image) {
        this.profile_image = profile_image;
    }

    public String getProfile_bio() {
        return profile_bio;
    }

    public void setProfile_bio(String profile_bio) {
        this.profile_bio = profile_bio;
    }
}