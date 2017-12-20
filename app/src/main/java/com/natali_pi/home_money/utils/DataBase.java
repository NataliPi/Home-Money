package com.natali_pi.home_money.utils;

import android.graphics.Bitmap;
import android.util.Base64;

import com.natali_pi.home_money.BasePresenter;
import com.natali_pi.home_money.models.Family;
import com.natali_pi.home_money.models.Human;
import com.natali_pi.home_money.models.LoginData;

import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.Observable;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by Natali-Pi on 09.12.2017.
 */

public class DataBase {

    private static DataBase instance = new DataBase();

    private DataBase() {}

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

    public void login(LoginData data) {
    family = data.getFamily();
    human = data.getHuman();
    }


}
