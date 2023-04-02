package com.example.a_assmr.Views.Credentials.Account.My_Property.ViewProperty.Vehicle.Controller;

import android.content.Context;
import android.widget.Toast;

import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.a_assmr.Common;
import com.example.a_assmr.CommonDir.GenericClassServerResponse;
import com.example.a_assmr.Views.Credentials.Account.My_Property.ViewProperty.ItemViewModel;
import com.example.a_assmr.Views.Credentials.Account.My_Property.ViewProperty.Vehicle.Interface.MyVehicleDBInterface;
import com.example.a_assmr.Views.Credentials.Account.My_Property.ViewProperty.Vehicle.Model.MyVehicleServerResponse;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MyVehicleController {
    Context context;
    Common common;
    ItemViewModel itemViewModel;
    public MyVehicleController(Context context, FragmentActivity fragmentActivity) {
        this.context = context;
        this.common = new Common();
        this.itemViewModel = new ViewModelProvider(fragmentActivity).get(ItemViewModel.class);
    }

    public void fetchMyPostedVehicle(int activeUserID) {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(this.common.getApiURI())
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();

        MyVehicleDBInterface dbInterface = retrofit.create(MyVehicleDBInterface.class);
        Call<MyVehicleServerResponse> call = dbInterface.myVehicleLists(activeUserID);
        call.enqueue(new Callback<MyVehicleServerResponse>() {
            @Override
            public void onResponse(Call<MyVehicleServerResponse> call, Response<MyVehicleServerResponse> response) {
                if(!response.isSuccessful()) {
                    Toast.makeText(context, "R: "+response.message(), Toast.LENGTH_LONG).show();
                    return;
                }
                GenericClassServerResponse<MyVehicleServerResponse> genericClassServerResponse = new GenericClassServerResponse<>();
                genericClassServerResponse.setCertainGenericClassServerResponse(response.body());
                itemViewModel.selectItem(genericClassServerResponse);
            }

            @Override
            public void onFailure(Call<MyVehicleServerResponse> call, Throwable t) {
                Toast.makeText(context, "F: "+t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}
