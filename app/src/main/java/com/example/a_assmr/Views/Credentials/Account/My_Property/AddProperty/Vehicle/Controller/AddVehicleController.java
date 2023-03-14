package com.example.a_assmr.Views.Credentials.Account.My_Property.AddProperty.Vehicle.Controller;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Toast;

import com.example.a_assmr.Common;
import com.example.a_assmr.Views.Credentials.Account.My_Property.AddProperty.Vehicle.Interface.AddVehicleDBInterface;
import com.example.a_assmr.Views.Credentials.Account.My_Property.AddProperty.Vehicle.Interface.AddVehicleFragmentInterface;
import com.example.a_assmr.Views.Credentials.Account.My_Property.AddProperty.Vehicle.Model.VehicleForm;
import com.example.a_assmr.Views.Credentials.Account.My_Property.AddProperty.Vehicle.Model.VehicleResponse;

import java.util.HashMap;

import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AddVehicleController extends BroadcastReceiver {
    Common common;
    Context context;

    public AddVehicleController(Context context) {
        this.context = context;
        this.common = new Common();
    }

    public void uploadVehicleImage(VehicleForm vehicleForm, AddVehicleFragmentInterface afi) {
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
        AddVehicleDBInterface vehicleDBInterface = retrofit.create(AddVehicleDBInterface.class);
        HashMap<String, MultipartBody.Part> map = new HashMap<>();
        map.put("name", MultipartBody.Part.createFormData("name", "please workk"));
        Call <VehicleResponse> call = vehicleDBInterface.uploadVehicleProperty(map);
        call.enqueue(new Callback<VehicleResponse>() {
            @Override
            public void onResponse(Call<VehicleResponse> call, Response<VehicleResponse> response) {
                if(!response.isSuccessful()) {
                    Toast.makeText(context, "R: "+response.message(), Toast.LENGTH_LONG).show();
                    return;
                }

            }

            @Override
            public void onFailure(Call<VehicleResponse> call, Throwable t) {
                Toast.makeText(context, "F: "+t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void onReceive(Context context, Intent intent) {
    }
}
