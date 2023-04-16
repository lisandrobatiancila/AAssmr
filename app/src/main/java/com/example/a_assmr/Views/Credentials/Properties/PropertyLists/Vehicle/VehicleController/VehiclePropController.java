package com.example.a_assmr.Views.Credentials.Properties.PropertyLists.Vehicle.VehicleController;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.example.a_assmr.Common;
import com.example.a_assmr.Views.Credentials.Properties.PropertyLists.Vehicle.Interface.VehicleDBInterface;
import com.example.a_assmr.Views.Credentials.Properties.PropertyLists.Vehicle.Interface.VehicleInterface;
import com.example.a_assmr.Views.Credentials.Properties.PropertyLists.Vehicle.VehicleModel.VehicleServerRequest;
import com.example.a_assmr.Views.Credentials.Properties.PropertyLists.Vehicle.VehicleModel.VehicleServerResponse;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class VehiclePropController extends BroadcastReceiver {
    VehicleInterface vehicleInterface;
    Context context;
    Common common;

    public VehiclePropController(Context context, VehicleInterface vehicleInterface) {
        this.vehicleInterface = vehicleInterface;
        this.context = context;
        this.common = new Common();
    }

    public void getVehicleProperties(VehicleServerRequest serverRequest) {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient httpClient = new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(common.getApiURI())
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient)
                .build();

        VehicleDBInterface vehicleDBInterface = retrofit.create(VehicleDBInterface.class);
        Call <VehicleServerResponse> call = vehicleDBInterface.vehicleLists(serverRequest.getPropStartID());
        call.enqueue(new Callback<VehicleServerResponse>() {
            @Override
            public void onResponse(Call<VehicleServerResponse> call, Response<VehicleServerResponse> response) {
                if(!response.isSuccessful()) {
                    Toast.makeText(context, "R: "+response.message(), Toast.LENGTH_SHORT).show();
                    return;
                }
                vehicleInterface.propertyLists(response.body().vehiclesList);
            }

            @Override
            public void onFailure(Call<VehicleServerResponse> call, Throwable t) {
                Toast.makeText(context, "F: "+t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }//this get the property, that based on ID ex: propID FROM 1...10 -> 11...20

    @Override
    public void onReceive(Context context, Intent intent) {

    }
}
