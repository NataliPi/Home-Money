package com.natali_pi.home_money.models;

import com.natali_pi.home_money.utils.Currency;

/**
 * Created by Natali-Pi on 21.11.2017.
 */

public class SpendingComponent {
    private String name;
    private Money price;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Money getPrice() {
        return price;
    }

    public void setPrice(Money price) {
        this.price = price;
    }
}
