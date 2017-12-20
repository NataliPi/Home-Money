package com.natali_pi.home_money.models;

/**
 * Created by Natali-Pi on 24.11.2017.
 */

public class Category {
    private String id;
    private String photo;
    private String name;
    public Category(String filliner) {
        this.name = filliner;
    }


    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public String getPhoto() {
        return photo;
    }

    public String getName() {
        return name;
    }
}
