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
import com.example.a_assmr.Views.Credentials.Account.My_Property.ViewProperty.Jewelry.MyJewelry;
import com.example.a_assmr.Views.Credentials.Account.My_Property.ViewProperty.Realestate.MyRealestate;
import com.example.a_assmr.Views.Credentials.Account.My_Property.ViewProperty.VURCertainProperty.Interface.VURDBInterface;
import com.example.a_assmr.Views.Credentials.Account.My_Property.ViewProperty.VURCertainProperty.Model.RemoveServerResponse;
import com.example.a_assmr.Views.Credentials.Account.My_Property.ViewProperty.Vehicle.MyVehicle;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RemoveCertainPropController {
    Context context;
    Common common;
    AlertDialog dialog;
    ItemViewModel itemViewModel;
    public RemoveCertainPropController(Context context, AlertDialog dialog) {
        this.context = context;
        this.dialog = dialog;
        this.itemViewModel = new ViewModelProvider((ViewModelStoreOwner) context).get(ItemViewModel.class);
        this.common = new Common();
    }

    public void removeCertainProperty(int propertyID, String propertyType, GenericClass genericClass) {
        Object obj = genericClass.getCertainClass();

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

        VURDBInterface vurdbInterface = retrofit.create(VURDBInterface.class);
        if(obj instanceof MyVehicle) {
            Call<RemoveServerResponse> call = vurdbInterface.removeCertainVehicleProperty(propertyID, propertyType);
            call.enqueue(new Callback<RemoveServerResponse>() {
                @Override
                public void onResponse(Call<RemoveServerResponse> call, Response<RemoveServerResponse> response) {
                    if(!response.isSuccessful()) {
                        dialog.dismiss();
                        Toast.makeText(context, "R: "+response.message(), Toast.LENGTH_LONG).show();
                        return;
                    }
                    dialog.dismiss();
                    GenericClassServerResponse<RemoveServerResponse> genericClassServerResponse = new GenericClassServerResponse<>();
                    genericClassServerResponse.setCertainGenericClassServerResponse(response.body());

                    itemViewModel.selectItem(genericClassServerResponse);
                }

                @Override
                public void onFailure(Call<RemoveServerResponse> call, Throwable t) {
                    dialog.dismiss();
                    Toast.makeText(context, "F: "+t.getMessage(), Toast.LENGTH_LONG).show();
                }
            });
        }
        else if(obj instanceof MyRealestate) {
            Call<RemoveServerResponse> call = vurdbInterface.removeCertainRealestate(propertyID, propertyType);
            call.enqueue(new Callback<RemoveServerResponse>() {
                @Override
                public void onResponse(Call<RemoveServerResponse> call, Response<RemoveServerResponse> response) {
                    dialog.dismiss();
                    if(!response.isSuccessful()) {
                        Toast.makeText(context, "R: "+response.message(), Toast.LENGTH_LONG).show();
                        return;
                    }
                    GenericClassServerResponse<RemoveServerResponse> genericClassServerResponse = new GenericClassServerResponse<>();
                    genericClassServerResponse.setCertainGenericClassServerResponse(response.body());
                    itemViewModel.selectItem(genericClassServerResponse);
                }

                @Override
                public void onFailure(Call<RemoveServerResponse> call, Throwable t) {
                    dialog.dismiss();
                    Toast.makeText(context, "F: "+t.getMessage(), Toast.LENGTH_LONG).show();
                }
            });
        }
        else if(obj instanceof MyJewelry) {
            Call<RemoveServerResponse> call = vurdbInterface.removeCertainJewelry(propertyID, propertyType);
            call.enqueue(new Callback<RemoveServerResponse>() {
                @Override
                public void onResponse(Call<RemoveServerResponse> call, Response<RemoveServerResponse> response) {
                    dialog.dismiss();
                    if(!response.isSuccessful()) {
                        Toast.makeText(context, "R: "+response.message(), Toast.LENGTH_LONG).show();
                        return;
                    }
                    GenericClassServerResponse<RemoveServerResponse> genericClassServerResponse = new GenericClassServerResponse<>();
                    genericClassServerResponse.setCertainGenericClassServerResponse(response.body());

                    itemViewModel.selectItem(genericClassServerResponse);
                }

                @Override
                public void onFailure(Call<RemoveServerResponse> call, Throwable t) {
                    dialog.dismiss();
                    Toast.makeText(context, "F: "+t.getMessage(), Toast.LENGTH_LONG).show();
                }
            });
        }
    }
}
