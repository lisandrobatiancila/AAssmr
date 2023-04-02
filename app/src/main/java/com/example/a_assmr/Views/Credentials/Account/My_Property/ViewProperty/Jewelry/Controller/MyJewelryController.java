package com.example.a_assmr.Views.Credentials.Account.My_Property.ViewProperty.Jewelry.Controller;

import android.app.AlertDialog;
import android.content.Context;
import android.widget.Toast;

import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.a_assmr.Common;
import com.example.a_assmr.CommonDir.GenericClassServerResponse;
import com.example.a_assmr.Views.Credentials.Account.My_Property.ViewProperty.ItemViewModel;
import com.example.a_assmr.Views.Credentials.Account.My_Property.ViewProperty.Jewelry.Interface.MyJewelryDBInterface;
import com.example.a_assmr.Views.Credentials.Account.My_Property.ViewProperty.Jewelry.Model.MyJewelryServerResponse;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MyJewelryController {
    Context context;
    Common common;
    ItemViewModel itemViewModel;
    AlertDialog dialog;
    public MyJewelryController(Context context, FragmentActivity fragmentActivity, AlertDialog dialog) {
        this.context = context;
        this.common = new Common();
        this.dialog = dialog;
        itemViewModel = new ViewModelProvider(fragmentActivity).get(ItemViewModel.class);
    }

    public void fetchMyPostedJewelries(int activeUserID) {
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

        MyJewelryDBInterface dbInterface = retrofit.create(MyJewelryDBInterface.class);
        Call<MyJewelryServerResponse> call = dbInterface.myJewelryLists(activeUserID);
        call.enqueue(new Callback<MyJewelryServerResponse>() {
            @Override
            public void onResponse(Call<MyJewelryServerResponse> call, Response<MyJewelryServerResponse> response) {
                if(!response.isSuccessful()) {
                    dialog.dismiss();
                    Toast.makeText(context, "R: "+response.message(), Toast.LENGTH_LONG).show();
                    return;
                }
                dialog.dismiss();
                GenericClassServerResponse<MyJewelryServerResponse> genericClassServerResponse = new GenericClassServerResponse<>();
                genericClassServerResponse.setCertainGenericClassServerResponse(response.body());

                itemViewModel.selectItem(genericClassServerResponse);
            }

            @Override
            public void onFailure(Call<MyJewelryServerResponse> call, Throwable t) {
                dialog.dismiss();
                Toast.makeText(context, "F: "+t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}
