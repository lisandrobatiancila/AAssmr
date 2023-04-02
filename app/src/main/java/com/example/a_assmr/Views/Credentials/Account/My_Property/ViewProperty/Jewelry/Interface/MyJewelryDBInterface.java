package com.example.a_assmr.Views.Credentials.Account.My_Property.ViewProperty.Jewelry.Interface;

import com.example.a_assmr.Views.Credentials.Account.My_Property.ViewProperty.Jewelry.Model.MyJewelryServerResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface MyJewelryDBInterface {
    @FormUrlEncoded
    @POST("/api/mobile-my-jewelry-properties")
    Call<MyJewelryServerResponse> myJewelryLists(@Field("userID") int userID);
}
