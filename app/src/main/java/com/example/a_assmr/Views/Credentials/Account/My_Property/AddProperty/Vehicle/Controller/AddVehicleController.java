package com.example.a_assmr.Views.Credentials.Account.My_Property.AddProperty.Vehicle.Controller;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.Toast;

import com.example.a_assmr.Common;
import com.example.a_assmr.CommonDir.GenericClass;
import com.example.a_assmr.CommonDir.GenericClassInterface;
import com.example.a_assmr.CommonDir.ImageProcesess;
import com.example.a_assmr.Views.Credentials.Account.My_Property.AddProperty.Vehicle.AddVehicleProperty;
import com.example.a_assmr.Views.Credentials.Account.My_Property.AddProperty.Vehicle.Interface.AddVehicleDBInterface;
import com.example.a_assmr.Views.Credentials.Account.My_Property.AddProperty.Vehicle.Interface.AddVehicleInterface;
import com.example.a_assmr.Views.Credentials.Account.My_Property.AddProperty.Vehicle.Model.AddVehicleForm;
import com.example.a_assmr.Views.Credentials.Account.My_Property.AddProperty.Vehicle.Model.AddVehicleResponse;
import com.google.gson.Gson;

import java.util.List;

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
    AddVehicleInterface addVehicleInterface;
    GenericClassInterface genericClassInterface;
    AddVehicleProperty addVehicleProperty;
    public AddVehicleController(Context context, AddVehicleInterface addVehicleInterface, GenericClassInterface genericClassInterface, AddVehicleProperty addVehicleProperty) {
        this.context = context;
        this.addVehicleInterface = addVehicleInterface;
        this.genericClassInterface = genericClassInterface;
        this.addVehicleProperty = addVehicleProperty;
        this.common = new Common();
    }

    public void uploadVehicleImage(Uri uri, int propertyID, AlertDialog dialog) {
        List<MultipartBody.Part> imageListst = ImageProcesess.ImageToLists(context, uri);

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
        MultipartBody.Part propID = MultipartBody.Part.createFormData("propertyID", String.valueOf(propertyID));
        Call<AddVehicleResponse> call = vehicleDBInterface.uploadVehicleProperty(imageListst, propID);

        call.enqueue(new Callback<AddVehicleResponse>() {
            @Override
            public void onResponse(Call<AddVehicleResponse> call, Response<AddVehicleResponse> response) {
                dialog.dismiss();
                if(!response.isSuccessful()) {
                    Toast.makeText(context, response.message(), Toast.LENGTH_LONG).show();
                    return;
                }
                addVehicleInterface.addVehicleProperty(response.body());
            }

            @Override
            public void onFailure(Call<AddVehicleResponse> call, Throwable t) {
                dialog.dismiss();
                Toast.makeText(context, "F: "+t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    } // this is used to upload single file
    public void uploadVehicleImage(ClipData clipData, int propertyID, AlertDialog dialog) {
        List<MultipartBody.Part> imageLists = ImageProcesess.ImageToLists(context, clipData);
        MultipartBody.Part propID = MultipartBody.Part.createFormData("propertyID", String.valueOf(propertyID));

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
        AddVehicleDBInterface avdbInterface = retrofit.create(AddVehicleDBInterface.class);
        Call<AddVehicleResponse> call = avdbInterface.uploadVehicleProperty(imageLists, propID);
        call.enqueue(new Callback<AddVehicleResponse>() {
            @Override
            public void onResponse(Call<AddVehicleResponse> call, Response<AddVehicleResponse> response) {
                dialog.dismiss();
                if(!response.isSuccessful()) {
                    Toast.makeText(context, "R: "+response.message(), Toast.LENGTH_LONG).show();
                    return;
                }
                GenericClass<AddVehicleProperty> gc = new GenericClass();
                gc.setCertainClass(addVehicleProperty);
                genericClassInterface.resetForm(gc);
                addVehicleInterface.addVehicleProperty(response.body());
            }

            @Override
            public void onFailure(Call<AddVehicleResponse> call, Throwable t) {
                dialog.dismiss();
                Toast.makeText(context, "F: "+t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    } // this is used to upload multiple files
    public void saveVehicleInfo(AddVehicleForm vehicleForm, int activeUserID, Intent imageData, AlertDialog dialog, AddVehicleProperty addVehicleProperty) {
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

        AddVehicleDBInterface adbInterface = retrofit.create(AddVehicleDBInterface.class);
        Gson gson = new Gson();
        String jsonForm = gson.toJson(vehicleForm);

        Call <AddVehicleResponse> call = adbInterface.uploadVehicleInfo(jsonForm, activeUserID);
        call.enqueue(new Callback<AddVehicleResponse>() {
            @Override
            public void onResponse(Call<AddVehicleResponse> call, Response<AddVehicleResponse> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(context, "R: "+response.message(), Toast.LENGTH_LONG).show();
                    return;
                }
                final int propertyID = response.body().propertyID;
                if(response.body().code == 200) {
                    if(imageData.getData() != null)
                        uploadVehicleImage(imageData.getData(), propertyID, dialog);
                    else if(imageData.getClipData() != null)
                        uploadVehicleImage(imageData.getClipData(), propertyID, dialog);
                }
                else
                    Toast.makeText(context, "We can not process your. request", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<AddVehicleResponse> call, Throwable t) {
                Toast.makeText(context, "F: "+t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    } // this save vehicle information
    @Override
    public void onReceive(Context context, Intent intent) {
    }
}