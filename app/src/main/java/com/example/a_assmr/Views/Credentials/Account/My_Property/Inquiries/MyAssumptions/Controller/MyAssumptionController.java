package com.example.a_assmr.Views.Credentials.Account.My_Property.Inquiries.MyAssumptions.Controller;

import android.content.Context;
import android.widget.Toast;

import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.a_assmr.Common;
import com.example.a_assmr.CommonDir.GenericClassServerResponse;
import com.example.a_assmr.Views.Credentials.Account.My_Property.Inquiries.MyAssumptions.Interface.MyAssumptionsDBInterface;
import com.example.a_assmr.Views.Credentials.Account.My_Property.Inquiries.MyAssumptions.Model.MyAssumptionsModel;
import com.example.a_assmr.Views.Credentials.Account.My_Property.ViewProperty.ItemViewModel;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MyAssumptionController {
    Context context;
    Common common;
    ItemViewModel itemViewModel;
    public MyAssumptionController(Context context, FragmentActivity storeOwner) {
        this.context = context;
        this.itemViewModel = new ViewModelProvider(storeOwner).get(ItemViewModel.class);
        this.common = new Common();
    }
    public void getMyAssumedProperties(int userID) {
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

        MyAssumptionsDBInterface dbInterface = retrofit.create(MyAssumptionsDBInterface.class);
        Call<MyAssumptionsModel> call = dbInterface.getAssumedProperties(userID);
        call.enqueue(new Callback<MyAssumptionsModel>() {
            @Override
            public void onResponse(Call<MyAssumptionsModel> call, Response<MyAssumptionsModel> response) {
                if(!response.isSuccessful()) {
                    Toast.makeText(context, "R: "+response.message(), Toast.LENGTH_LONG).show();
                    return;
                }
                GenericClassServerResponse<MyAssumptionsModel> genericClassServerResponse = new GenericClassServerResponse<>();
                genericClassServerResponse.setCertainGenericClassServerResponse(response.body());
                itemViewModel.selectItem(genericClassServerResponse);
            }

            @Override
            public void onFailure(Call<MyAssumptionsModel> call, Throwable t) {
                Toast.makeText(context, "F: t"+t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}
