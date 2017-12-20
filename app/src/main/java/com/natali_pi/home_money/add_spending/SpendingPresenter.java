package com.natali_pi.home_money.add_spending;

import android.graphics.Bitmap;

import com.natali_pi.home_money.BasePresenter;
import com.natali_pi.home_money.models.Category;
import com.natali_pi.home_money.models.Message;
import com.natali_pi.home_money.models.Spending;
import com.natali_pi.home_money.utils.Api;
import com.natali_pi.home_money.utils.BaseAPI;
import com.natali_pi.home_money.utils.DataBase;

/**
 * Created by Natali-Pi on 22.11.2017.
 */

public class SpendingPresenter extends BasePresenter<AddSpendingActivity> {

    private BaseAPI api = new Api();
    private Category category;
    private static SpendingPresenter instance = new SpendingPresenter();

    public static SpendingPresenter getInstance() {

        return instance;
    }



    public void setCategory(Category category) {

        if (category.getId() == null) {
            api.addCategory(DataBase.getInstance().getFamily().getId(), category.getName(), category.getName())
                    .subscribe(getObserver(true, (response) -> {
                        this.category = category;
                        category.setId(response.getResult());
                        DataBase.getInstance().getFamily().getCategories().add(category);
                        getView().updateCategoriesList();
                        getView().toSpendig();
                    }));

        } else {
            this.category = category;
            getView().toSpendig();
        }

    }


    public Category getCategory() {
        return category;
    }

    public void setSpending(Spending spending) {
        spending.setBuyerId(DataBase.getInstance().getFamily().getId());
        api.setSpending(spending).subscribe(getObserver(true, (result) -> {
            getView().finish();
        }));

    }

    public void uploadCategoryPhoto(Category category, Bitmap bitmap) {
        api.uploadPicture(new Message(bitmap), DataBase.getInstance().getFamily().getId(), category.getId())
                .subscribe(getObserver(true, (result) -> {
                    category.setPhoto(result.getResult());
                    getView().updateCategoriesList();
                }));
    }
}
