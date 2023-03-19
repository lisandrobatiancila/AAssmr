package com.example.a_assmr.Views.Credentials.Account.My_Property.AddProperty.Realestate.Controller;

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
import com.example.a_assmr.Views.Credentials.Account.My_Property.AddProperty.Realestate.AddRealestateProperty;
import com.example.a_assmr.Views.Credentials.Account.My_Property.AddProperty.Realestate.Interface.AddRealesateInterface;
import com.example.a_assmr.Views.Credentials.Account.My_Property.AddProperty.Realestate.Interface.AddRealestateDBInterface;
import com.example.a_assmr.Views.Credentials.Account.My_Property.AddProperty.Realestate.Model.AddRealestateForm;
import com.example.a_assmr.Views.Credentials.Account.My_Property.AddProperty.Realestate.Model.AddRealestateResponse;
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

public class AddRealestateController {
    Context context;
    AddRealesateInterface addRealesateInterface;
    GenericClassInterface genericClassInterface;
    AddRealestateProperty addRealestateProperty;
    Common common;
    public AddRealestateController(Context context, AddRealesateInterface addRealesateInterface, GenericClassInterface genericClassInterface, AddRealestateProperty addRealestateProperty) {
        this.context = context;
        this.addRealesateInterface = addRealesateInterface;
        this.genericClassInterface = genericClassInterface;
        this.addRealestateProperty = addRealestateProperty;
        this.common = new Common();
    }
    public void uploadRealestateImage(Uri uri, int propertyID, AlertDialog dialog) {
        List<MultipartBody.Part> imageLists = ImageProcesess.ImageToLists(context, uri);
        MultipartBody.Part partPropertyID = MultipartBody.Part.createFormData("propertyID", String.valueOf(propertyID));

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

        AddRealestateDBInterface ARDBI = retrofit.create(AddRealestateDBInterface.class);
        Call<AddRealestateResponse> call = ARDBI.addRealestateImage(imageLists, partPropertyID);
        call.enqueue(new Callback<AddRealestateResponse>() {
            @Override
            public void onResponse(Call<AddRealestateResponse> call, Response<AddRealestateResponse> response) {
                if(!response.isSuccessful()) {
                    Toast.makeText(context, "R: "+response.message(), Toast.LENGTH_LONG).show();
                    return;
                }
                addRealesateInterface.addRealestateProperty(response.body());
                GenericClass<AddRealestateProperty> gc = new GenericClass<>();
                gc.setCertainClass(addRealestateProperty);
                genericClassInterface.resetForm(gc);
            }

            @Override
            public void onFailure(Call<AddRealestateResponse> call, Throwable t) {
                Toast.makeText(context, "F: "+t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
    public void uploadRealestateImage(ClipData clipImage, int propertyID, AlertDialog dialog) {
        MultipartBody.Part propPropertyID = MultipartBody.Part.createFormData("propertyID", String.valueOf(propertyID));

        List<MultipartBody.Part> imageLists = ImageProcesess.ImageToLists(context, clipImage);
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
        AddRealestateDBInterface ARDBI = retrofit.create(AddRealestateDBInterface.class);
        Call<AddRealestateResponse> call = ARDBI.addRealestateImage(imageLists, propPropertyID);
        call.enqueue(new Callback<AddRealestateResponse>() {
            @Override
            public void onResponse(Call<AddRealestateResponse> call, Response<AddRealestateResponse> response) {
                dialog.dismiss();
                if(!response.isSuccessful()) {
                    Toast.makeText(context, "R: "+response.message(), Toast.LENGTH_LONG).show();
                    return;
                }
                addRealesateInterface.addRealestateProperty(response.body());
                GenericClass<AddRealestateProperty> gc = new GenericClass<>();
                gc.setCertainClass(addRealestateProperty);
                genericClassInterface.resetForm(gc);
            }

            @Override
            public void onFailure(Call<AddRealestateResponse> call, Throwable t) {
                dialog.dismiss();
                Toast.makeText(context, "F: "+t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    public void saveRealestateInfo(AddRealestateForm realForm, int userID, AlertDialog dialog, Intent imageData) {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        Gson gson = new Gson();
        String jsonForm = gson.toJson(realForm);

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(common.getApiURI())
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();

        AddRealestateDBInterface ARDBInt = retrofit.create(AddRealestateDBInterface.class);
        Call<AddRealestateResponse> call = ARDBInt.addRealestateInfo(jsonForm, userID);
        call.enqueue(new Callback<AddRealestateResponse>() {
            @Override
            public void onResponse(Call<AddRealestateResponse> call, Response<AddRealestateResponse> response) {
                if(!response.isSuccessful()) {
                    Toast.makeText(context, "R: "+response.message(), Toast.LENGTH_LONG).show();
                    return;
                }
                final int propertyID = response.body().propertyID;

                if(response.body().code == 200) {
                    if(imageData.getClipData() != null) {
                        uploadRealestateImage(imageData.getClipData(), propertyID, dialog);
                    }
                    else if(imageData.getData() != null) {
                        uploadRealestateImage(imageData.getData(), propertyID, dialog);
                    }
                }
                else
                    Toast.makeText(context, "We can not process your request."+response.body().message, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<AddRealestateResponse> call, Throwable t) {
                Toast.makeText(context, "F: "+t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}
