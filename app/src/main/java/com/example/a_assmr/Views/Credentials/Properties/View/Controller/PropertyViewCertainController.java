package com.example.a_assmr.Views.Credentials.Properties.View.Controller;

import android.content.Context;
import android.widget.Toast;

import com.example.a_assmr.Common;
import com.example.a_assmr.CommonDir.GenericClass;
import com.example.a_assmr.Views.Credentials.Properties.View.Interface.PVCDBInterface;
import com.example.a_assmr.Views.Credentials.Properties.View.Interface.PVCInterface;
import com.example.a_assmr.Views.Credentials.Properties.View.Model.PVCJewelryModel;
import com.example.a_assmr.Views.Credentials.Properties.View.Model.PVCRealestateModel;
import com.example.a_assmr.Views.Credentials.Properties.View.Model.PVCVehicleModel;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PropertyViewCertainController {
    Context context;
    Common common;
    PVCInterface pvcInterface;

    public PropertyViewCertainController(Context context, PVCInterface pvcInterface) {
        this.context = context;
        this.common = new Common();
        this.pvcInterface = pvcInterface;
    }

    public void getCertainVehicleProperty(int propertyID) {
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
        PVCDBInterface pvcdbInterface = retrofit.create(PVCDBInterface.class);
        Call<PVCVehicleModel> call = pvcdbInterface.getCertainVehicle(propertyID);

        call.enqueue(new Callback<PVCVehicleModel>() {
            @Override
            public void onResponse(Call<PVCVehicleModel> call, Response<PVCVehicleModel> response) {
                if(!response.isSuccessful()) {
                    Toast.makeText(context, "R: "+response.message(), Toast.LENGTH_LONG).show();
                    return;
                }
                GenericClass<PVCVehicleModel> genericClass = new GenericClass<>();
                genericClass.setCertainClass(response.body());

                pvcInterface.getCertainProperty(genericClass);
            }

            @Override
            public void onFailure(Call<PVCVehicleModel> call, Throwable t) {
                Toast.makeText(context, "F: "+t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
    public void getCertainRealestateProperty(int propertyID) {
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

        PVCDBInterface pvcdbInterface = retrofit.create(PVCDBInterface.class);
        Call<PVCRealestateModel> call = pvcdbInterface.getCertainRealestate(propertyID);
        call.enqueue(new Callback<PVCRealestateModel>() {
            @Override
            public void onResponse(Call<PVCRealestateModel> call, Response<PVCRealestateModel> response) {
                if(!response.isSuccessful()) {
                    Toast.makeText(context, "R: "+response.message(), Toast.LENGTH_LONG).show();
                    return;
                }
                GenericClass<PVCRealestateModel> genericClass = new GenericClass<>();
                genericClass.setCertainClass(response.body());

                pvcInterface.getCertainProperty(genericClass);
            }

            @Override
            public void onFailure(Call<PVCRealestateModel> call, Throwable t) {
                Toast.makeText(context, "F: "+t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
    public void getCertainJewelryProperty(int propertyID) {
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

        PVCDBInterface pvcdbInterface = retrofit.create(PVCDBInterface.class);
        Call<PVCJewelryModel> call = pvcdbInterface.getCertainJewelry(propertyID);
        call.enqueue(new Callback<PVCJewelryModel>() {
            @Override
            public void onResponse(Call<PVCJewelryModel> call, Response<PVCJewelryModel> response) {
                if(!response.isSuccessful()) {
                    Toast.makeText(context, "R: "+response.message(), Toast.LENGTH_LONG).show();
                    return;
                }
                GenericClass<PVCJewelryModel> genericClass = new GenericClass<>();
                genericClass.setCertainClass(response.body());

                pvcInterface.getCertainProperty(genericClass);
            }

            @Override
            public void onFailure(Call<PVCJewelryModel> call, Throwable t) {
                Toast.makeText(context, "F: "+t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}