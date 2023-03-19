package com.example.a_assmr.Views.Credentials.Account.My_Property.AddProperty.Vehicle.Interface;

import com.example.a_assmr.Views.Credentials.Account.My_Property.AddProperty.Vehicle.Model.AddVehicleResponse;

import java.util.List;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface AddVehicleDBInterface {
    @Multipart
    @POST("/api/mobile-upload-vehicle-image")
    Call<AddVehicleResponse> uploadVehicleProperty(@Part List<MultipartBody.Part> file, @Part MultipartBody.Part propertyID);
//    Call<AddVehicleResponse> uploadVehicleProperty(@Part List<MultipartBody.Part> file, @Part MultipartBody.Part form);
    @FormUrlEncoded
    @POST("/api/mobile-upload-vehicle-info")
    Call <AddVehicleResponse> uploadVehicleInfo(@Field("form") String form, @Field("userID") int userID);
}