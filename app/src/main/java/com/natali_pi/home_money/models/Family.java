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
    public Spending getSpendingById(String id) {
        for (Spending spending : spendings) {
            if(spending.getId().equals(id)){
                return spending;
            }
        }
        return null;
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

    public void setSpending(Spending spending) {
        //this.spending = spending;//TODO set spending
        for (int i = 0; i < spendings.size(); i++) {
            if(spendings.get(i).getId().equals(spending.getId())){
                spendings.set(i, spending);
                return;
            }
        }
        spendings.add(spending);
    }

    public ArrayList<ArrayList<Spending>> getSpendingsByMMonth() {
        ArrayList<ArrayList<Spending>> result = new ArrayList<ArrayList<Spending>>();
        for (int i = 0; i < spendings.size(); i++) {//крутим все траты
            boolean monthFound = false;
            for (ArrayList<Spending> spendingsInMonth : result) {//ищем есть ли такой месяц уже в результате

                if (spendingsInMonth.get(0).getSpendingMonth() == spendings.get(i).getSpendingMonth()) {
                    spendingsInMonth.add(spendings.get(i));
                    monthFound = true;
                    break;
                }

            }
            if (!monthFound) {
                ArrayList<Spending> newSpendings = new ArrayList<>();
                newSpendings.add(this.spendings.get(i));
                result.add(newSpendings);
            }
        }


            int n = result.size();
            int k;
            for (int m = n; m >= 0; m--) {
                for (int i = 0; i < n - 1; i++) {
                    k = i + 1;
                    if (result.get(i).get(0).getSpendingMonth() < result.get(k).get(0).getSpendingMonth()) {
                        result.set(k, result.set(i, result.get(k)));
                    }
                }

            }

        return result;
    }

}
