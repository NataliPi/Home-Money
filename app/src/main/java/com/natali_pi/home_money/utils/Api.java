package com.natali_pi.home_money.utils;

import com.natali_pi.home_money.models.Family;
import com.natali_pi.home_money.models.Human;
import com.natali_pi.home_money.models.LoginData;
import com.natali_pi.home_money.models.Message;
import com.natali_pi.home_money.models.Spending;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by Natali-Pi on 13.12.2017.
 */

public class Api implements BaseAPI {
    private static BaseAPI api;
    public Api(){
        if (api == null){
            api = App.getBaseApi();
        }
    }
    @Override
    public Observable<List<Family>> test() {
        return api.test().compose(new AsyncTransformer<>());
    }

    @Override
    public Observable<Message> register(Human human) {
        return api.register(human).compose(new AsyncTransformer<>());
    }

    @Override
    public Observable<LoginData> login(Human human) {
        return api.login(human).compose(new AsyncTransformer<>());
    }

    @Override
    public Observable<Message> addCategory(String familyId, String categoryId, String name) {
        return api.addCategory(familyId,categoryId,name).compose(new AsyncTransformer<>());
    }

    @Override
    public Observable<Message> setSpending(Spending spending) {
        return api.setSpending(spending).compose(new AsyncTransformer<>());
    }

    @Override
    public Observable<Message> uploadPicture(Message message, String id) {
        return api.uploadPicture(message, id).compose(new AsyncTransformer<>());
    }

    @Override
    public Observable<Message> uploadPicture(Message message, String familyId, String id) {
        return api.uploadPicture(message, familyId, id).compose(new AsyncTransformer<>());
    }


}
