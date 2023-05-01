package com.example.a_assmr.Views.Credentials.Account.My_Property.Inquiries.MyAssumptions.Interface;

import com.example.a_assmr.Views.Credentials.Account.My_Property.Inquiries.MyAssumptions.Model.MyAssumptionsModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface MyAssumptionsDBInterface {
    @GET("/api/get-my-assumptions/{userID}")
    Call<MyAssumptionsModel> getAssumedProperties(@Path("userID") int userID);
}
