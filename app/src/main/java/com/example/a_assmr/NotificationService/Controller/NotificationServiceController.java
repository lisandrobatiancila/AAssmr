package com.example.a_assmr.NotificationService.Controller;

import android.content.Context;
import android.widget.Toast;

import com.example.a_assmr.Common;
import com.example.a_assmr.NotificationService.Interface.NotificationServiceDBInterface;
import com.example.a_assmr.NotificationService.Model.NotificationServiceModel;

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

    public NotificationServiceController(Context context) {
        this.context = context;
        this.common = new Common();
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
        Call<NotificationServiceModel> call = notificationServiceDBInterface.getNotifications(userID, userEmail);
        call.enqueue(new Callback<NotificationServiceModel>() {
            @Override
            public void onResponse(Call<NotificationServiceModel> call, Response<NotificationServiceModel> response) {
                if(!response.isSuccessful()) {
                    Toast.makeText(context, "R: "+response.message(), Toast.LENGTH_LONG).show();
                    return;
                }
            }

            @Override
            public void onFailure(Call<NotificationServiceModel> call, Throwable t) {
                Toast.makeText(context, "F: "+t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}
