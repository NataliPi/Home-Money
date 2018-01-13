package com.natali_pi.home_money.models;

import com.natali_pi.home_money.utils.App;

/**
 * Created by Natali-Pi on 21.11.2017.
 */

public class Human {
    private String name;
    private String password;
    private String email;
    private String photo;
    private String id;
    private String familyName;
    private String familyId;

    public String getId() {
        return id;
    }

    public String getPhoto() {
        if(photo != null && !photo.equals("")) {
            return App.BASE_URL + App.PICTURE_URL + photo;
        } else {
            return null;
        }
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public void setPhoto(String photo) {

        this.photo = photo;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }
}
