package com.example.a_assmr.Views.Credentials.Account.My_Property.Inquiries.MyAssumptions.Interface;

import com.example.a_assmr.CommonDir.StandardResponse;
import com.example.a_assmr.Views.Credentials.Account.My_Property.Inquiries.MyAssumptions.Model.MyAssumptionsModel;
import com.example.a_assmr.Views.Credentials.Account.My_Property.Inquiries.MyAssumptions.OwnerInformation.Model.OwnerModelContainer;
import com.example.a_assmr.Views.Credentials.Account.My_Property.Inquiries.MyAssumptions.PropertyDetails.Model.PropertyDetailsModel;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface MyAssumptionsDBInterface {
    @GET("/api/get-my-assumptions/{userID}")
    Call<MyAssumptionsModel> getAssumedProperties(@Path("userID") int userID);
    // get all assumed properties of the active user
    @GET("/api/get-my-certain-assumed-property/{propertyType}/{propertyID}/{itemID}")
    Call<PropertyDetailsModel> getMyCertainAssumedProperty(@Path("propertyType") String propertyType, @Path("propertyID") int propertyID, @Path("itemID") int itemID);
    // get record of certain property
    @GET("/api/get-owner-information/{ownerID}")
    Call<OwnerModelContainer> getOwnerInformation(@Path("ownerID") int ownerID);
    // get property owner information

    @FormUrlEncoded
    @POST("/api/assumer-cancel-assumption")
    Call<StandardResponse> cancelMyAssumption(@Field("userID") int userID,
                                              @Field("assumptionID") int assumptionID,
                                              @Field("propertyID") int propertyID);
}
