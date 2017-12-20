package com.natali_pi.home_money.models;

import com.natali_pi.home_money.BuildConfig;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Natali-Pi on 21.11.2017.
 */

public class Spending {
    private String name;
    private String buyerId;
    private String id;
    private String category;
    private String date;
    private String photo;
    private List<SpendingComponent> components;

    public void setComponents(List<SpendingComponent> components) {
        this.components = components;
    }

    public void setDate(String date) {
        try {
            this.date = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX").format(new SimpleDateFormat("dd/MM/yyyy").parse(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public void setBuyerId(String buyerId) {
        this.buyerId = buyerId;
    }

    public Spending(String name) {
        this.name = name;

    }
    private Money getSumTest(){
        return new Money(Integer.parseInt(name));
    }
    public Money getSum (){
        if(BuildConfig.DEBUG){
            return getSumTest();
        }
        Money result = new Money();
        for (int i=0; i<=components.size(); i++){
            result = Money.sum(result,components.get(i).getPrice());
        }
        return result;
    }
}
