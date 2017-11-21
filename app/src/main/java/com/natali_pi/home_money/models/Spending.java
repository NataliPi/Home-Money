package com.natali_pi.home_money.models;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Natali-Pi on 21.11.2017.
 */

public class Spending {
    private String name;
    private String buyerId;
    private String category;
    private Date date;
    private String photo;
    private ArrayList<SpendingComponent> components;
}
