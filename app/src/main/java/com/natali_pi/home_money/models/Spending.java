package com.natali_pi.home_money.models;

import com.natali_pi.home_money.BuildConfig;
import com.natali_pi.home_money.utils.App;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Natali-Pi on 21.11.2017.
 */

public class Spending implements Serializable {
    private String name;
    private String buyerId;
    private String id;
    private String category;
    private String date;
    private String photo;
    private Money summ;
    private List<SpendingComponent> components;

    public void setComponents(List<SpendingComponent> components) {
        this.components = components;
    }

    public String getCategory() {
        return category;
    }

    public void setDate(String date) {
        try {
            this.date = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS").format(new SimpleDateFormat("dd/MM/yyyy").parse(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public String getName() {
        return name;
    }

    public String getDate() {
        return date;
    }

    public String getPhoto() {
        if (photo != null && !photo.equals("")) {
            return App.BASE_URL + App.PICTURE_URL + photo;
        } else {
            return null;
        }
    }

    public void setSumm(Money summ) {
        this.summ = summ;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setBuyerId(String buyerId) {
        this.buyerId = buyerId;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Spending(String name) {
        this.name = name;

    }
    private Money getSumTest(){
        try {
            return new Money(Integer.parseInt(name));
        } catch (NumberFormatException nfe){
            return new Money(0);
        }
    }
    public Money getSum (){
        if(summ == null) {
            if (BuildConfig.DEBUG) {
                return getSumTest();
            }
            Money result = new Money();
            for (int i = 0; i <= components.size(); i++) {
                result = Money.sum(result, components.get(i).getPrice());
            }
            return result;
        } else {
            return summ;
        }
    }
}
