package com.example.a_assmr.Views.Credentials.Account.My_Property.AddProperty.Jewelry.Interface;

import com.example.a_assmr.Views.Credentials.Account.My_Property.AddProperty.Jewelry.Model.AddJewelryResponse;

import java.util.List;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface AddJewelryDBInterface {
    @Multipart
    @POST("/api/mobile-upload-jewelry-image")
    Call<AddJewelryResponse> uploadJewelryImage(@Part List<MultipartBody.Part> imageLists, @Part MultipartBody.Part propertyID);
    @FormUrlEncoded
    @POST("/api/mobile-upload-jewelry-info")
    Call<AddJewelryResponse> addJewelryInfo(@Field("jewelryForm") String jewelryForm, @Field("userID") int userID);
}
