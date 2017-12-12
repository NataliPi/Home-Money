package com.natali_pi.home_money.models;

import com.natali_pi.home_money.BuildConfig;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Natali-Pi on 21.11.2017.
 */

public class Spending {
    private String name;
    private String buyerId;
    private String id;
    private String category;
    private Date date;
    private String photo;
    private ArrayList<SpendingComponent> components;

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
