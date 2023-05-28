package com.example.a_assmr.Views.Credentials.Account.My_Property.Messages.Interface;

import com.example.a_assmr.Views.Credentials.Account.My_Property.Messages.Model.MessagesModelContainer;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface MessageInterface {
    @FormUrlEncoded
    @POST("/api/mobile-get-list-messages")
    Call<MessagesModelContainer> getActiveUserMessagesLists(@Field("message_reciever") String message_reciever, @Field("userID") int userID);
}
