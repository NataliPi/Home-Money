package com.natali_pi.home_money.add_spending;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.natali_pi.home_money.BaseActivity;
import com.natali_pi.home_money.BaseFragment;
import com.natali_pi.home_money.BasePresenter;
import com.natali_pi.home_money.R;
import com.natali_pi.home_money.models.Category;
import com.natali_pi.home_money.models.Spending;
import com.natali_pi.home_money.utils.Api;
import com.natali_pi.home_money.utils.BaseAPI;
import com.natali_pi.home_money.utils.DataBase;

/**
 * Created by Natali-Pi on 22.11.2017.
 */

public class SpendingPresenter extends BasePresenter{
    AddSpendingActivity activity;
    private BaseAPI api;
    Category category;
    public SpendingPresenter(AddSpendingActivity activity) {
        this.activity = activity;
        api = new Api();
    }

    public void setCategory(Category category) {

        if(category.getId() == null){
            api.addCategory(DataBase.getInstance().getFamily().getId(),"", category.getName())
            .subscribe(getObserver(true, (response)->{
                this.category = category;
                category.setId(response.getResult());
                DataBase.getInstance().getFamily().getCategories().add(category);
                activity.updateCategoriesList();
                activity.toSpendig();
            }));

        } else {
            this.category = category;
            activity.toSpendig();
        }

    }

    @Override
    protected BaseActivity getView() {
        return activity;
    }

    public Category getCategory() {
        return category;
    }

    public void setSpending(Spending spending) {
        spending.setBuyerId(DataBase.getInstance().getFamily().getId());
        api.setSpending(spending).subscribe(getObserver(true, (result)->{
            getView().finish();
        }));

    }
}
