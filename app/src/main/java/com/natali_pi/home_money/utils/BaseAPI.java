package com.natali_pi.home_money.utils;

import com.natali_pi.home_money.models.Family;
import com.natali_pi.home_money.models.Human;
import com.natali_pi.home_money.models.LoginData;
import com.natali_pi.home_money.models.Message;
import com.natali_pi.home_money.models.Spending;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by Natali-Pi on 13.12.2017.
 */

public interface BaseAPI {
        @GET("/")
        Observable<List<Family>> test();

        @POST("/rest/register/")
        Observable<Message> register(@Body Human human);

        @POST("/rest/login/")
        Observable<LoginData> login(@Body Human human);

        @GET("/rest/category/add/{familyId}/{categoryId}/{name}/")
        Observable<Message> addCategory(@Path("familyId") String familyId, @Path("categoryId") String categoryId, @Path("name") String name);

        @POST("/rest/spending/add/")
        Observable<Message> setSpending(@Body Spending spending);


}