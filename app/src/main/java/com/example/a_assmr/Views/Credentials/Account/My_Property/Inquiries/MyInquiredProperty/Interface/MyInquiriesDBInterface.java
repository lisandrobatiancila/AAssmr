package com.example.a_assmr.Views.Credentials.Account.My_Property.Inquiries.MyInquiredProperty.Interface;

import com.example.a_assmr.Views.Credentials.Account.My_Property.Inquiries.MyInquiredProperty.Model.MyInquiryModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface MyInquiriesDBInterface {
    @GET("/api/get-my-inquired-properties/{userID}")
    Call<MyInquiryModel> getInquiredProperties(@Path("userID") int userID);
}
