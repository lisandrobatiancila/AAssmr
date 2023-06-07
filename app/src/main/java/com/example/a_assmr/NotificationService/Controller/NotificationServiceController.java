package com.example.a_assmr.NotificationService.Controller;

import android.content.Context;
import android.widget.Toast;

import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;

import com.example.a_assmr.Common;
import com.example.a_assmr.CommonDir.GenericClassServerResponse;
import com.example.a_assmr.NotificationService.Interface.NotificationResponseInterface;
import com.example.a_assmr.NotificationService.Interface.NotificationServiceDBInterface;
import com.example.a_assmr.NotificationService.Model.NotificationServiceModel;
import com.example.a_assmr.NotificationService.Model.NotificationServiceModelContainer;
import com.example.a_assmr.NotificationService.NotificationService;
import com.example.a_assmr.Views.Credentials.Account.My_Property.ViewProperty.ItemViewModel;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NotificationServiceController {
    Context context;
    Common common;
    NotificationResponseInterface notificationResponseInterface;
    public NotificationServiceController(Context context, NotificationService notificationService) {
        this.context = context;
        this.common = new Common();
        this.notificationResponseInterface = notificationService;
    }

    public void getNotifications(int userID, String userEmail) {
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
        NotificationServiceDBInterface notificationServiceDBInterface = retrofit.create(NotificationServiceDBInterface.class);
        Call<NotificationServiceModelContainer> call = notificationServiceDBInterface.getNotifications(userID, userEmail);
        call.enqueue(new Callback<NotificationServiceModelContainer>() {
            @Override
            public void onResponse(Call<NotificationServiceModelContainer> call, Response<NotificationServiceModelContainer> response) {
                if(!response.isSuccessful()) {
                    Toast.makeText(context, "R: "+response.message(), Toast.LENGTH_LONG).show();
                    return;
                }
                notificationResponseInterface.notificationResponse(response.body());
            }

            @Override
            public void onFailure(Call<NotificationServiceModelContainer> call, Throwable t) {
                Toast.makeText(context, "F: "+t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}
