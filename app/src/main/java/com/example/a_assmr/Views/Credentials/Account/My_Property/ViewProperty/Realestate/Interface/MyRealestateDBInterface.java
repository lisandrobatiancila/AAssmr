package com.example.a_assmr.Views.Credentials.Account.My_Property.ViewProperty.Realestate.Interface;

import com.example.a_assmr.Views.Credentials.Account.My_Property.ViewProperty.Realestate.Model.MyRealestateServerResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface MyRealestateDBInterface {
    @FormUrlEncoded
    @POST("/api/mobile-my-realestate-properties")
    Call<MyRealestateServerResponse> myRealestateLists(@Field("userID") int userID);
}
