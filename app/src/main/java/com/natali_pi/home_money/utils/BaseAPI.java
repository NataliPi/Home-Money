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
//TODO: delete before production
    Observable<List<Family>> test();

    @POST("/rest/register/")
    Observable<Message> register(@Body Human human);

    @POST("/rest/login/")
    Observable<LoginData> login(@Body Human human);

    @GET("/rest/category/add/{familyId}/{categoryId}/{name}/")
    Observable<Message> addCategory(@Path("familyId") String familyId, @Path("categoryId") String categoryId, @Path("name") String name);

    @POST("/rest/spending/add/")
    Observable<Message> setSpending(@Body Spending spending);


    @POST("/rest/picture/upload/{id}")
    Observable<Message> uploadPicture(@Body Message message, @Path("id") String id);

    //    @RequestMapping(value = "/rest/picture/upload/{familyId}/{id}", method = RequestMethod.POST)
    //public Message uploadPicture(@RequestBody Message message, @PathVariable String familyId, @PathVariable String id) {
    @POST("/rest/picture/upload/{familyId}/{id}")
    Observable<Message> uploadPicture(@Body Message message, @Path("familyId") String familyId, @Path("id") String id);

    @GET("/rest/family/invite/{familyId}")
    Observable<Message> prepareInvitation(@Path("familyId") String familyId);

    //(@PathVariable String familyId, @PathVariable String humanId, @PathVariable String password) {
    @GET("/rest/family/accept/{familyId}/{humanId}/{password}")
    Observable<Message> acceptInvitation(@Path("familyId") String familyId, @Path("humanId") String humanId, @Path("password") String password);

}
