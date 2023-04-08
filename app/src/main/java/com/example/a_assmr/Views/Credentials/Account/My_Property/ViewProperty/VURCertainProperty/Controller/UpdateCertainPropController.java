package com.example.a_assmr.Views.Credentials.Account.My_Property.ViewProperty.VURCertainProperty.Controller;

import android.app.AlertDialog;
import android.content.Context;
import android.widget.Toast;

import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;

import com.example.a_assmr.Common;
import com.example.a_assmr.CommonDir.GenericClass;
import com.example.a_assmr.CommonDir.GenericClassServerResponse;
import com.example.a_assmr.Views.Credentials.Account.My_Property.ViewProperty.ItemViewModel;
import com.example.a_assmr.Views.Credentials.Account.My_Property.ViewProperty.VURCertainProperty.Interface.VURDBInterface;
import com.example.a_assmr.Views.Credentials.Account.My_Property.ViewProperty.VURCertainProperty.Model.UpdateJewelryModel;
import com.example.a_assmr.Views.Credentials.Account.My_Property.ViewProperty.VURCertainProperty.Model.UpdateRealestateModel;
import com.example.a_assmr.Views.Credentials.Account.My_Property.ViewProperty.VURCertainProperty.Model.UpdateServerResponse;
import com.example.a_assmr.Views.Credentials.Account.My_Property.ViewProperty.VURCertainProperty.Model.UpdateVehicleModel;

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

    public UpdateCertainPropController(Context context, GenericClass genericClass, AlertDialog dialog,
                                       GenericClass viewModelOwner) {
        this.genericClass = genericClass;
        this.context = context;
        this.dialog = dialog;
        this.common = new Common();
        this.viewModel = new ViewModelProvider((ViewModelStoreOwner) viewModelOwner.getCertainClass()).get(ItemViewModel.class);
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
        else if(obj instanceof UpdateRealestateModel) {
            VURDBInterface vurdbInterface = retrofit.create(VURDBInterface.class);
            Call<UpdateServerResponse> call = vurdbInterface.updateRealestate((UpdateRealestateModel) obj);
            call.enqueue(new Callback<UpdateServerResponse>() {
                @Override
                public void onResponse(Call<UpdateServerResponse> call, Response<UpdateServerResponse> response) {
                    dialog.dismiss();
                    if(!response.isSuccessful()) {
                        Toast.makeText(context, "R: "+response.message(), Toast.LENGTH_LONG).show();
                        return;
                    }
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
        }
        else if(obj instanceof UpdateJewelryModel) {
            VURDBInterface vurdbInterface = retrofit.create(VURDBInterface.class);
            Call<UpdateServerResponse> call = vurdbInterface.updateJewelry((UpdateJewelryModel) genericClass.getCertainClass());
            call.enqueue(new Callback<UpdateServerResponse>() {
                @Override
                public void onResponse(Call<UpdateServerResponse> call, Response<UpdateServerResponse> response) {
                    dialog.dismiss();
                    if(!response.isSuccessful()) {
                        Toast.makeText(context, "R: "+response.message(), Toast.LENGTH_LONG).show();
                        return;
                    }
                    GenericClassServerResponse<UpdateServerResponse> genericClassServerResponse = new GenericClassServerResponse<>();
                    genericClassServerResponse.setCertainGenericClassServerResponse(response.body());

                    viewModel.selectItem(genericClassServerResponse);
                }

                @Override
                public void onFailure(Call<UpdateServerResponse> call, Throwable t) {
                    dialog.dismiss();
                    Toast.makeText(context, "F: "+t.getMessage(), Toast.LENGTH_LONG).show();
                }
            });
        }
    }
}
