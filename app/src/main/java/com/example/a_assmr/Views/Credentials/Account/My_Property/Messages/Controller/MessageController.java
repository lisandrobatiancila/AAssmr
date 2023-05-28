package com.example.a_assmr.Views.Credentials.Account.My_Property.Messages.Controller;

import android.content.Context;
import android.widget.Toast;

import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.a_assmr.Common;
import com.example.a_assmr.CommonDir.GenericClassServerResponse;
import com.example.a_assmr.Views.Credentials.Account.My_Property.Messages.Interface.MessageInterface;
import com.example.a_assmr.Views.Credentials.Account.My_Property.Messages.Model.MessagesModelContainer;
import com.example.a_assmr.Views.Credentials.Account.My_Property.ViewProperty.ItemViewModel;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MessageController {
    Common common;
    Context context;
    ItemViewModel itemViewModel;
    public MessageController(Context context, FragmentActivity fragmentActivity) {
        this.context = context;
        this.common = new Common();
        this.itemViewModel = new ViewModelProvider(fragmentActivity).get(ItemViewModel.class);
    }
    public void getMessages(String message_reciever, int userID) {
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

        MessageInterface messageInterface = retrofit.create(MessageInterface.class);
        Call<MessagesModelContainer> call = messageInterface.getActiveUserMessagesLists(message_reciever, userID);
        call.enqueue(new Callback<MessagesModelContainer>() {
            @Override
            public void onResponse(Call<MessagesModelContainer> call, Response<MessagesModelContainer> response) {
                if(!response.isSuccessful()) {
                    Toast.makeText(context, "R: "+response.message(), Toast.LENGTH_SHORT).show();
                    return;
                }

                GenericClassServerResponse<MessagesModelContainer> genericClassServerResponse = new GenericClassServerResponse<>();
                genericClassServerResponse.setCertainGenericClassServerResponse(response.body());

                itemViewModel.selectItem(genericClassServerResponse);
            }

            @Override
            public void onFailure(Call<MessagesModelContainer> call, Throwable t) {
                Toast.makeText(context, "F: "+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    } //get active user-messages
}
