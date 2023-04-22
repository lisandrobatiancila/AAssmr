package com.example.a_assmr.Views.Credentials.Account.My_Property.FeedBacks.Interface;

import com.example.a_assmr.CommonDir.StandardResponse;
import com.example.a_assmr.Views.Credentials.Account.My_Property.FeedBacks.Model.FeedBackForm;
import com.example.a_assmr.Views.Credentials.Account.My_Property.FeedBacks.Model.FeedBackServerResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface FeedBackDBInterface {
    @POST("/api/add-new-feedbacks")
    Call<StandardResponse> addNewFeedBack(@Body FeedBackForm feedBackForm);
    @GET("/api/get-all-feedbacks")
    Call<FeedBackServerResponse> getAllFeedBacks();
}
