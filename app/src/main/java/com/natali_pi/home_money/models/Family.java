package com.natali_pi.home_money.models;

import java.util.ArrayList;

/**
 * Created by Natali-Pi on 21.11.2017.
 */

public class Family {
    private String familyid;
    private String name;
    private ArrayList<Spending> spended;
    private ArrayList<Spending> plannedSpends;
    private ArrayList<String> humanIds;
    private Money limit;

    public Family() {
        this.name = "Пятковские";
        this.spended = new ArrayList<>();
        spended.add(new Spending("1501"));
        //spended.add(new Spending("1401"));
        spended.add(new Spending("951"));
        spended.add(new Spending("921"));
        spended.add(new Spending("900"));
        spended.add(new Spending("860"));
        spended.add(new Spending("850"));
        spended.add(new Spending("840"));
        spended.add(new Spending("830"));
        spended.add(new Spending("100"));
        this.plannedSpends = plannedSpends;
        this.humanIds = humanIds;
        this.limit = limit;
    }

    public String getName() {
        return name;
    }

    public String getFamilyid() {
        return familyid;
    }

    public ArrayList<Spending> getSpended() {
        return spended;
    }

    public ArrayList<Spending> getPlannedSpends() {
        return plannedSpends;
    }

    public ArrayList<String> getHumanIds() {
        return humanIds;
    }

    public Money getLimit() {
        return limit;
    }
}
