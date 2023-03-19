package com.example.a_assmr.Views.Credentials.Account.My_Property.AddProperty.Jewelry.Controller;

import android.app.AlertDialog;
import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.Toast;

import com.example.a_assmr.Common;
import com.example.a_assmr.CommonDir.GenericClass;
import com.example.a_assmr.CommonDir.GenericClassInterface;
import com.example.a_assmr.CommonDir.ImageProcesess;
import com.example.a_assmr.Views.Credentials.Account.My_Property.AddProperty.Jewelry.AddJewelryProperty;
import com.example.a_assmr.Views.Credentials.Account.My_Property.AddProperty.Jewelry.Interface.AddJewelryDBInterface;
import com.example.a_assmr.Views.Credentials.Account.My_Property.AddProperty.Jewelry.Interface.AddJewelryInterface;
import com.example.a_assmr.Views.Credentials.Account.My_Property.AddProperty.Jewelry.Model.AddJewelryForm;
import com.example.a_assmr.Views.Credentials.Account.My_Property.AddProperty.Jewelry.Model.AddJewelryResponse;
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

public class AddJewelryController {
    Context context;
    AddJewelryInterface addJewelryInterface;
    GenericClassInterface genericClassInterface;
    Common common;
    AddJewelryProperty addJewelryProperty;
    public AddJewelryController(Context context, AddJewelryInterface addJewelryInterface, AddJewelryProperty addJewelryProperty, GenericClassInterface genericClassInterface) {
        this.context = context;
        this.addJewelryInterface = addJewelryInterface;
        this.addJewelryProperty = addJewelryProperty;
        this.genericClassInterface = genericClassInterface;
        this.common = new Common();
    }
    public void uploadJewelryIMAGE(Uri uri, int propertyID, AlertDialog dialog) {

    }
    public void uploadJewelryIMAGE(ClipData clipData, int propertyID, AlertDialog dialog) {
        List<MultipartBody.Part> imageLists = ImageProcesess.ImageToLists(context, clipData);
        MultipartBody.Part partPropertyID = MultipartBody.Part.createFormData("propertyID", String.valueOf(propertyID));

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

        AddJewelryDBInterface AJDBI = retrofit.create(AddJewelryDBInterface.class);
        Call<AddJewelryResponse> call = AJDBI.uploadJewelryImage(imageLists, partPropertyID);
        call.enqueue(new Callback<AddJewelryResponse>() {
            @Override
            public void onResponse(Call<AddJewelryResponse> call, Response<AddJewelryResponse> response) {
                dialog.dismiss();
                if(!response.isSuccessful()) {
                    Toast.makeText(context, "R: "+response.message(), Toast.LENGTH_LONG).show();
                    return;
                }
                addJewelryInterface.addJewelryProperty(response.body());

                GenericClass<AddJewelryProperty> gc = new GenericClass<>();
                gc.setCertainClass(addJewelryProperty);
                genericClassInterface.resetForm(gc);
            }

            @Override
            public void onFailure(Call<AddJewelryResponse> call, Throwable t) {
                dialog.dismiss();
                Toast.makeText(context, "F: "+t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
    public void saveJewelryInfo(AddJewelryForm addJewelryForm, int userID, AlertDialog dialog, Intent imageData) {
        Gson gson = new Gson();
        String jsonForm = gson.toJson(addJewelryForm);

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

        AddJewelryDBInterface AJDI = retrofit.create(AddJewelryDBInterface.class);
        Call<AddJewelryResponse> call = AJDI.addJewelryInfo(jsonForm, userID);
        call.enqueue(new Callback<AddJewelryResponse>() {
            @Override
            public void onResponse(Call<AddJewelryResponse> call, Response<AddJewelryResponse> response) {
                if(!response.isSuccessful()) {
                    Toast.makeText(context, "R: "+response.message(), Toast.LENGTH_LONG).show();
                    return;
                }
                if(response.body().code == 200) {
                    if(imageData.getClipData() != null) {
                        uploadJewelryIMAGE(imageData.getClipData(), response.body().propertyID, dialog);
                    }
                    else if(imageData.getData() != null) {
                        uploadJewelryIMAGE(imageData.getData(), response.body().propertyID, dialog);
                    }
                }
                else
                    Toast.makeText(context, response.body().propertyID, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<AddJewelryResponse> call, Throwable t) {
                Toast.makeText(context, "F: "+t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}
