package com.natali_pi.home_money.models;

import java.util.ArrayList;

/**
 * Created by Natali-Pi on 21.11.2017.
 */

public class Family {
    private String familyid;
    private ArrayList<Spending> spended;
    private ArrayList<Spending> plannedSpends;
    private ArrayList<String> humanIds;
    private Money limit;
}
