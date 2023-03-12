package com.example.a_assmr.Views.Credentials.Properties.Fragments.Jewelry.JewelryController;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.example.a_assmr.Common;
import com.example.a_assmr.Views.Credentials.Properties.Fragments.Jewelry.JewelryInterface.JewelryDBInterface;
import com.example.a_assmr.Views.Credentials.Properties.Fragments.Jewelry.JewelryInterface.JewelryInterface;
import com.example.a_assmr.Views.Credentials.Properties.Fragments.Jewelry.JewelryModel.JewelryServerResponse;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class JewelryController extends BroadcastReceiver {
    Context context;
    Common common;
    JewelryInterface jewelryInterface;

    public JewelryController(Context context, JewelryInterface jewelryInterface) {
        this.context = context;
        this.jewelryInterface = jewelryInterface;
        this.common = new Common();
    }

    public void getJewelryLists(int propStartID) {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(common.getApiURI())
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();
        JewelryDBInterface dbInterface = retrofit.create(JewelryDBInterface.class);
        Call< JewelryServerResponse> call = dbInterface.getJewelryLists(propStartID);
        call.enqueue(new Callback<JewelryServerResponse>() {
            @Override
            public void onResponse(Call<JewelryServerResponse> call, Response<JewelryServerResponse> response) {
                if(!response.isSuccessful()) {
                    Toast.makeText(context, "R: "+response.message(), Toast.LENGTH_LONG).show();
                    return;
                }
                jewelryInterface.getJewelryLists(response.body().jewelryLists);
            }

            @Override
            public void onFailure(Call<JewelryServerResponse> call, Throwable t) {
                Toast.makeText(context, "F: "+t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }//this get the property, that based on ID ex: propID FROM 1...10 -> 11...20

    @Override
    public void onReceive(Context context, Intent intent) {

    }
}
