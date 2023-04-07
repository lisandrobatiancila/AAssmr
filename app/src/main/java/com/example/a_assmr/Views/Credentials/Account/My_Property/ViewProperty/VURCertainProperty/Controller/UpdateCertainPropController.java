package com.example.a_assmr.Views.Credentials.Account.My_Property.ViewProperty.VURCertainProperty.Controller;

import android.content.Context;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.lifecycle.ViewModelProvider;

import com.example.a_assmr.Common;
import com.example.a_assmr.CommonDir.GenericClass;
import com.example.a_assmr.CommonDir.GenericClassServerResponse;
import com.example.a_assmr.Views.Credentials.Account.My_Property.ViewProperty.VURCertainProperty.Interface.VURDBInterface;
import com.example.a_assmr.Views.Credentials.Account.My_Property.ViewProperty.VURCertainProperty.Model.UpdateServerResponse;
import com.example.a_assmr.Views.Credentials.Account.My_Property.ViewProperty.VURCertainProperty.Model.UpdateVehicleModel;
import com.example.a_assmr.Views.Credentials.Account.My_Property.ViewProperty.VURCertainProperty.UpdateCertainVehicle;
import com.example.a_assmr.Views.Credentials.Account.My_Property.ViewProperty.ItemViewModel;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UpdateCertainPropController {
    GenericClass genericClass;
    Common common;
    Context context;
    AlertDialog dialog;
    ItemViewModel viewModel;

    public UpdateCertainPropController(Context context, GenericClass genericClass, AlertDialog dialog, UpdateCertainVehicle viewModelOwner) {
        this.genericClass = genericClass;
        this.context = context;
        this.dialog = dialog;
        this.common = new Common();
        this.viewModel = new ViewModelProvider(viewModelOwner).get(ItemViewModel.class);
    }

    public void updateCertainProperty(int propertyID, String propertyType, GenericClass genericClass) {
        Object obj = genericClass.getCertainClass();

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

        if(obj instanceof UpdateVehicleModel) {
            VURDBInterface vurdbInterface = retrofit.create(VURDBInterface.class);
            Call<UpdateServerResponse> call = vurdbInterface.updateVehicle((UpdateVehicleModel) obj);
            call.enqueue(new Callback<UpdateServerResponse>() {
                @Override
                public void onResponse(Call<UpdateServerResponse> call, Response<UpdateServerResponse> response) {
                    if(!response.isSuccessful()) {
                        dialog.dismiss();
                        Toast.makeText(context, "R: "+response.message(), Toast.LENGTH_LONG).show();
                        return;
                    }
                    dialog.dismiss();

                    GenericClassServerResponse<UpdateServerResponse> genericClassServerResponse = new GenericClassServerResponse();
                    genericClassServerResponse.setCertainGenericClassServerResponse(response.body());

                    viewModel.selectItem(genericClassServerResponse);
                }
                @Override
                public void onFailure(Call<UpdateServerResponse> call, Throwable t) {
                    dialog.dismiss();
                    Toast.makeText(context, "F: "+t.getMessage(), Toast.LENGTH_LONG).show();
                }
            });
        } // means user update the vehicle information
    }
}
