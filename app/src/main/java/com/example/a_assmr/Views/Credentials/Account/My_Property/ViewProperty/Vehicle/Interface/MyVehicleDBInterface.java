package com.example.a_assmr.Views.Credentials.Account.My_Property.ViewProperty.Vehicle.Interface;

import com.example.a_assmr.Views.Credentials.Account.My_Property.ViewProperty.Vehicle.Model.MyVehicleServerResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface MyVehicleDBInterface {
    @FormUrlEncoded
    @POST("/api/mobile-my-vehicle-properties")
    Call<MyVehicleServerResponse> myVehicleLists(@Field("userID") int userID);
}
