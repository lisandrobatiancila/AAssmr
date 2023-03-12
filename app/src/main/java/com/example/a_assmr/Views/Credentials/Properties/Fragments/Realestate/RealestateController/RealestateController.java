package com.example.a_assmr.Views.Credentials.Properties.Fragments.Realestate.RealestateController;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.example.a_assmr.Common;
import com.example.a_assmr.Views.Credentials.Properties.Fragments.Realestate.RealestateInterface.RealestateDBInterface;
import com.example.a_assmr.Views.Credentials.Properties.Fragments.Realestate.RealestateInterface.RealestateInterface;
import com.example.a_assmr.Views.Credentials.Properties.Fragments.Realestate.RealestateModel.Realestate;
import com.example.a_assmr.Views.Credentials.Properties.Fragments.Realestate.RealestateModel.RealestateServerResponse;

import okhttp3.OkHttp;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RealestateController extends BroadcastReceiver {
    Context context;
    Common common;
    RealestateInterface realestateInterface;

    public RealestateController(Context context, RealestateInterface realestateInterface) {
        this.context = context;
        this.realestateInterface = realestateInterface;
        this.common = new Common();
    }
    public void getRealestateLists(int propStartID) {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(common.getApiURI())
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();
        RealestateDBInterface realestateDBInterface = retrofit.create(RealestateDBInterface.class);
        Call <RealestateServerResponse> call = realestateDBInterface.realestateLists(propStartID);
        call.enqueue(new Callback<RealestateServerResponse>() {
            @Override
            public void onResponse(Call<RealestateServerResponse> call, Response<RealestateServerResponse> response) {
                if(!response.isSuccessful()) {
                    Toast.makeText(context, response.message(), Toast.LENGTH_LONG).show();
                    return;
                }
                realestateInterface.getRealestateLists(response.body().realestateLists);
            }

            @Override
            public void onFailure(Call<RealestateServerResponse> call, Throwable t) {
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }//this get the property, that based on ID ex: propID FROM 1...10 -> 11...20

    @Override
    public void onReceive(Context context, Intent intent) {

    }
}
