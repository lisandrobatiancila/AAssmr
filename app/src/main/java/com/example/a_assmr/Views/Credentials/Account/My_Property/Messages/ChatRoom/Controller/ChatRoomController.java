package com.example.a_assmr.Views.Credentials.Account.My_Property.Messages.ChatRoom.Controller;

import android.content.Context;
import android.widget.Toast;

import androidx.lifecycle.ViewModelProvider;

import com.example.a_assmr.Common;
import com.example.a_assmr.CommonDir.GenericClassServerResponse;
import com.example.a_assmr.CommonDir.StandardResponse;
import com.example.a_assmr.Views.Credentials.Account.My_Property.Messages.ChatRoom.ChatRoom;
import com.example.a_assmr.Views.Credentials.Account.My_Property.Messages.ChatRoom.Interface.ChatRoomInterface;
import com.example.a_assmr.Views.Credentials.Account.My_Property.Messages.ChatRoom.Model.GetMessageModelContainer;
import com.example.a_assmr.Views.Credentials.Account.My_Property.Messages.ChatRoom.Model.SendMessageModel;
import com.example.a_assmr.Views.Credentials.Account.My_Property.ViewProperty.ItemViewModel;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ChatRoomController {
    Common common;
    Context context;
    ItemViewModel itemViewModel;
    public ChatRoomController(Context context , ChatRoom chatRoom) {
        this.common = new Common();
        this.context = context;
        this.itemViewModel = new ViewModelProvider(chatRoom).get(ItemViewModel.class);
    }
    public void getMessages(String inboundUser, String outboundUser) {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(common.getApiURI())
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ChatRoomInterface chatRoomInterface = retrofit.create(ChatRoomInterface.class);
        Call<GetMessageModelContainer> call = chatRoomInterface.getMessages(inboundUser, outboundUser);
        call.enqueue(new Callback<GetMessageModelContainer>() {
            @Override
            public void onResponse(Call<GetMessageModelContainer> call, Response<GetMessageModelContainer> response) {
                if(!response.isSuccessful()) {
                    Toast.makeText(context, "R: "+response.message(), Toast.LENGTH_LONG).show();
                    return;
                }
                GenericClassServerResponse<GetMessageModelContainer> genericClassServerResponse = new GenericClassServerResponse<>();
                genericClassServerResponse.setCertainGenericClassServerResponse(response.body());
                itemViewModel.selectItem(genericClassServerResponse);
            }

            @Override
            public void onFailure(Call<GetMessageModelContainer> call, Throwable t) {
                Toast.makeText(context, "F: "+t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
    public void sendMessage(SendMessageModel sendMessageModel) {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(this.common.getApiURI())
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ChatRoomInterface chatRoomInterface = retrofit.create(ChatRoomInterface.class);
        Call<StandardResponse> call = chatRoomInterface.sendMessage(sendMessageModel);

        call.enqueue(new Callback<StandardResponse>() {
            @Override
            public void onResponse(Call<StandardResponse> call, Response<StandardResponse> response) {
                if(!response.isSuccessful()) {
                    Toast.makeText(context, "R: "+response.message(), Toast.LENGTH_SHORT).show();
                    return;
                }
            }

            @Override
            public void onFailure(Call<StandardResponse> call, Throwable t) {
                Toast.makeText(context, "F: "+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
