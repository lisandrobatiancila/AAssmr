package com.example.a_assmr.Views.Credentials.Account.My_Property.ViewProperty.VURCertainProperty.Interface;

import com.example.a_assmr.Views.Credentials.Account.My_Property.ViewProperty.VURCertainProperty.Model.RemoveServerResponse;
import com.example.a_assmr.Views.Credentials.Account.My_Property.ViewProperty.VURCertainProperty.Model.UpdateJewelryModel;
import com.example.a_assmr.Views.Credentials.Account.My_Property.ViewProperty.VURCertainProperty.Model.UpdateRealestateModel;
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
    // UPDATE
    @POST("/api/update-realestate-property")
    Call<UpdateServerResponse> updateRealestate(@Body UpdateRealestateModel realestateModel);
    @POST("/api/update-jewelry-property")
    Call<UpdateServerResponse> updateJewelry(@Body UpdateJewelryModel jewelryModel);
    @FormUrlEncoded
    @POST("/api/mobile-remove-vehicle")
    Call<RemoveServerResponse> removeCertainVehicleProperty(@Field("propertyID") int propertyID, @Field("propertyType") String propertyType);
    // We can use this multiple times; REMOVE
    @FormUrlEncoded
    @POST("/api/mobile-remove-realestates")
    Call<RemoveServerResponse> removeCertainRealestate(@Field("propertyID") int propertyID, @Field("propertyType") String propertyType);
    //We can use the above END-POINT; REMOVE
    @FormUrlEncoded
    @POST("/api/mobile-remove-jewelry")
    Call<RemoveServerResponse> removeCertainJewelry(@Field("propertyID") int propertyID, @Field("propertyType") String propertyType);
    //REDUNDANT LOGIN; we can use the above function; in removing properties;
}
