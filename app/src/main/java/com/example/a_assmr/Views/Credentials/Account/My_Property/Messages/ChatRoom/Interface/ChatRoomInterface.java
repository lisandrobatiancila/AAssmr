package com.example.a_assmr.Views.Credentials.Account.My_Property.Messages.ChatRoom.Interface;

import com.example.a_assmr.CommonDir.StandardResponse;
import com.example.a_assmr.Views.Credentials.Account.My_Property.Messages.ChatRoom.Model.GetMessageModelContainer;
import com.example.a_assmr.Views.Credentials.Account.My_Property.Messages.ChatRoom.Model.SendMessageModel;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ChatRoomInterface {
    @FormUrlEncoded
    @POST("/api/mobile-get-messages")
    Call<GetMessageModelContainer> getMessages(@Field("inboundEmail") String inbound, @Field("outboundEmail") String outbound);
    @POST("/api/mobile-send-message")
    Call<StandardResponse> sendMessage(@Body SendMessageModel message);
}
