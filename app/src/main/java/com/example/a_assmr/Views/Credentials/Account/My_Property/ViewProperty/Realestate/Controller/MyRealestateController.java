package com.example.a_assmr.Views.Credentials.Account.My_Property.ViewProperty.Realestate.Controller;

import android.content.Context;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.a_assmr.Common;
import com.example.a_assmr.CommonDir.GenericClassServerResponse;
import com.example.a_assmr.Views.Credentials.Account.My_Property.ViewProperty.ItemViewModel;
import com.example.a_assmr.Views.Credentials.Account.My_Property.ViewProperty.Realestate.Interface.MyRealestateDBInterface;
import com.example.a_assmr.Views.Credentials.Account.My_Property.ViewProperty.Realestate.Model.MyRealestateServerResponse;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MyRealestateController {
    Context context;
    Common common;
    AlertDialog dialog;
    ItemViewModel itemViewModel;

    public MyRealestateController(Context context, FragmentActivity fragmentActivity, AlertDialog dialog) {
        this.context = context;
        this.itemViewModel = new ViewModelProvider(fragmentActivity).get(ItemViewModel.class);
        this.dialog = dialog;
        this.common = new Common();
    }

    public void fetchMyPostedRealestates(int userID) {
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
        MyRealestateDBInterface dbInterface = retrofit.create(MyRealestateDBInterface.class);
        Call<MyRealestateServerResponse> call = dbInterface.myRealestateLists(userID);

        call.enqueue(new Callback<MyRealestateServerResponse>() {
            @Override
            public void onResponse(Call<MyRealestateServerResponse> call, Response<MyRealestateServerResponse> response) {
                if(!response.isSuccessful()) {
                    dialog.dismiss();
                    Toast.makeText(context, "R: "+response.message(), Toast.LENGTH_LONG).show();
                    return;
                }
                dialog.dismiss();
                    GenericClassServerResponse<MyRealestateServerResponse> genericClassServerResponse = new GenericClassServerResponse();
                genericClassServerResponse.setCertainGenericClassServerResponse(response.body());

                itemViewModel.selectItem(genericClassServerResponse);
            }

            @Override
            public void onFailure(Call<MyRealestateServerResponse> call, Throwable t) {
                dialog.dismiss();
                Toast.makeText(context, "F: "+t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}
