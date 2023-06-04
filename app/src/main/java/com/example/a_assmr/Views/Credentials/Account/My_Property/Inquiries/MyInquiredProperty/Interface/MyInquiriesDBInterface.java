package com.example.a_assmr.Views.Credentials.Account.My_Property.Inquiries.MyInquiredProperty.Interface;

import com.example.a_assmr.CommonDir.StandardResponse;
import com.example.a_assmr.Views.Credentials.Account.My_Property.Inquiries.MyInquiredProperty.AssumerInformation.Model.AssumerInformationModel;
import com.example.a_assmr.Views.Credentials.Account.My_Property.Inquiries.MyInquiredProperty.AssumerInformation.Model.AssumerInformationModelContainer;
import com.example.a_assmr.Views.Credentials.Account.My_Property.Inquiries.MyInquiredProperty.Model.MyInquiryModel;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface MyInquiriesDBInterface {
    @GET("/api/get-my-inquired-properties/{userID}")
    Call<MyInquiryModel> getInquiredProperties(@Path("userID") int userID);

    @GET("/api/get-all-assumer-information/{propertyID}")
    Call<AssumerInformationModelContainer> getAssumerInformationLists(@Path("propertyID") int propertyID);
    // this get all lists of assumer, who assumed the property of active user
    @FormUrlEncoded
    @POST("/api/cancel-assumer-assumption")
    Call<StandardResponse> cancelUserAssumption(@Field("assumerID") int assumerID);
    // property owner remove / cancel the assumer assumptions
}
