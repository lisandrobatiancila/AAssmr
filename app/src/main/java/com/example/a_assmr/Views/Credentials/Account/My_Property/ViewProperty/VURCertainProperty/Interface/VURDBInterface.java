package com.example.a_assmr.Views.Credentials.Account.My_Property.ViewProperty.VURCertainProperty.Interface;

import com.example.a_assmr.Views.Credentials.Account.My_Property.ViewProperty.VURCertainProperty.Model.RemoveServerResponse;
import com.example.a_assmr.Views.Credentials.Account.My_Property.ViewProperty.VURCertainProperty.Model.UpdateServerResponse;
import com.example.a_assmr.Views.Credentials.Account.My_Property.ViewProperty.VURCertainProperty.Model.UpdateVehicleModel;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface VURDBInterface {
    @POST("/api/update-vehicle-property")
    Call<UpdateServerResponse> updateVehicle(@Body UpdateVehicleModel vehicle);
    @FormUrlEncoded
    @POST("/api/mobile-remove-vehicle")
    Call<RemoveServerResponse> removeCertainProperty(@Field("propertyID") int propertyID, @Field("propertyType") String propertyType);
}
