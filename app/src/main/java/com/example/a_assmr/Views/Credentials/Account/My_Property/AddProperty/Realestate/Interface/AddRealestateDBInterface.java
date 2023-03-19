package com.example.a_assmr.Views.Credentials.Account.My_Property.AddProperty.Realestate.Interface;

import com.example.a_assmr.Views.Credentials.Account.My_Property.AddProperty.Realestate.Model.AddRealestateResponse;

import java.util.List;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface AddRealestateDBInterface {
    @FormUrlEncoded
    @POST("/api/mobile-upload-realestate-info")
    Call<AddRealestateResponse> addRealestateInfo(@Field("realForm") String realForm, @Field("userID") int userID);
    @Multipart
    @POST("/api/mobile-upload-realestate-image")
    Call<AddRealestateResponse> addRealestateImage(@Part List<MultipartBody.Part> file, @Part MultipartBody.Part propertyID);
}
