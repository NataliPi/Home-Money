package com.natali_pi.home_money.utils;

import android.graphics.Bitmap;
import android.util.Base64;

import com.natali_pi.home_money.models.Category;
import com.natali_pi.home_money.models.Family;
import com.natali_pi.home_money.models.Human;
import com.natali_pi.home_money.models.LoginData;

import java.io.ByteArrayOutputStream;

import io.reactivex.functions.Consumer;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;

/**
 * Created by Natali-Pi on 09.12.2017.
 */

public class DataBase {

    private static DataBase instance = new DataBase();

    Subject<Human> humanObservable = PublishSubject.create();
    private DataBase() {
    }

    public static DataBase getInstance() {
        return instance;
    }

    private Family family = null;
    private Human human = null;

    public Family getFamily() {
        return family;
    }

    public Human getHuman() {
        return human;
    }
    public void subscribeOnHuman(Consumer<Human> action){

        humanObservable.subscribe(action);
        try {
            action.accept(human);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void setHuman(Human human) {
        this.human = human;
        humanObservable.onNext(human);
    }

    private String convertBitmapToString(Bitmap photo) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        String photoBase64 = null;
        if (photo != null) {
            photo.compress(Bitmap.CompressFormat.JPEG, 30, byteArrayOutputStream);
            byte[] byteArray = byteArrayOutputStream.toByteArray();
            photoBase64 = Base64.encodeToString(byteArray, Base64.DEFAULT);
        }
        return photoBase64;
    }

    public String getCurrentCurrency() {
        return "UAH";
    }

    public void login(LoginData data) {
        family = data.getFamily();
        human = data.getHuman();
    }


    public Category getCategoryById(String categoryId) {
        for (int i = 0; i < family.getCategories().size(); i++) {
            if (family.getCategories().get(i).getId().equals(categoryId)) {
                return family.getCategories().get(i);
            }
        }
        return new Category("");

    }
}
