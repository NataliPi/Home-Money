package com.natali_pi.home_money.models;

import java.util.ArrayList;

/**
 * Created by Natali-Pi on 21.11.2017.
 */

public class Family {
    private String id;
    private String name;
    private ArrayList<Spending> spendings;
    private ArrayList<Spending> plannedSpendings;
    private ArrayList<String> humanIds;
    private ArrayList<Category> categories;
    private Money limit;

    public Family() {
    //    this.name = "Пятковские";
        /*this.spended = new ArrayList<>();
        spended.add(new Spending("1501"));
        //spended.add(new Spending("1401"));
        spended.add(new Spending("951"));
        spended.add(new Spending("921"));
        spended.add(new Spending("900"));
        spended.add(new Spending("830"));
        spended.add(new Spending("825"));
        spended.add(new Spending("100"));
        spended.add(new Spending("80"));
        spended.add(new Spending("90"));
        this.plannedSpends = plannedSpends;*/
        this.humanIds = humanIds;
        this.limit = limit;
    }

    public ArrayList<Category> getCategories() {
        return categories;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Spending> getSpendings() {
        return spendings;
    }

    public ArrayList<Spending> getPlannedSpendings() {
        return plannedSpendings;
    }

    public String getId() {
        return id;
    }

    public ArrayList<String> getHumanIds() {
        return humanIds;
    }

    public Money getLimit() {
        return limit;
    }
}
