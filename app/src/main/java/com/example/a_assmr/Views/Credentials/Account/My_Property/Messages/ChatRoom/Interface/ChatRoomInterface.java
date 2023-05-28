package com.example.a_assmr.Views.Credentials.Account.My_Property.Messages.ChatRoom.Interface;

import com.example.a_assmr.CommonDir.StandardResponse;
import com.example.a_assmr.Views.Credentials.Account.My_Property.Messages.ChatRoom.Model.GetMessageModelContainer;
import com.example.a_assmr.Views.Credentials.Account.My_Property.Messages.ChatRoom.Model.SendMessageModel;

import java.util.List;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface ChatRoomInterface {
    @FormUrlEncoded
    @POST("/api/mobile-get-messages")
    Call<GetMessageModelContainer> getMessages(@Field("inboundEmail") String inbound, @Field("outboundEmail") String outbound);
    // get the messages between 2 user; open the CHAT-ROOM
    @POST("/api/mobile-send-message-TEXT")
    Call<StandardResponse> sendTEXTMessage(@Body SendMessageModel message);
    // send message to other user; TEXT-only

    @Multipart
    @POST("/api/mobile-send-message-IMAGE")
    Call<StandardResponse> sendIMAGEMessage(@Part List<MultipartBody.Part> file, @Part MultipartBody.Part userID,
                          @Part MultipartBody.Part inboundUser,
                          @Part MultipartBody.Part outboundUser
    );
}
