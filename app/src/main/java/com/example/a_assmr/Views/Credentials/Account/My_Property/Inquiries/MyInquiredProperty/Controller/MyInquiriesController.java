package com.example.a_assmr.Views.Credentials.Account.My_Property.Inquiries.MyInquiredProperty.Controller;

import android.content.Context;
import android.widget.Toast;

import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.a_assmr.Common;
import com.example.a_assmr.CommonDir.GenericClassServerResponse;
import com.example.a_assmr.Views.Credentials.Account.My_Property.Inquiries.MyInquiredProperty.Interface.MyInquiriesDBInterface;
import com.example.a_assmr.Views.Credentials.Account.My_Property.Inquiries.MyInquiredProperty.Model.MyInquiryModel;
import com.example.a_assmr.Views.Credentials.Account.My_Property.ViewProperty.ItemViewModel;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MyInquiriesController {
    Context context;
    ItemViewModel itemViewModel;
    Common common;
    public MyInquiriesController(Context context, FragmentActivity fragmentActivity) {
        this.context = context;
        this.itemViewModel = new ViewModelProvider(fragmentActivity).get(ItemViewModel.class);
        this.common = new Common();
    }

    public void getMyInquiredProperty(int userID) {
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

        MyInquiriesDBInterface dbInterface = retrofit.create(MyInquiriesDBInterface.class);
        Call<MyInquiryModel> call = dbInterface.getInquiredProperties(userID);
        call.enqueue(new Callback<MyInquiryModel>() {
            @Override
            public void onResponse(Call<MyInquiryModel> call, Response<MyInquiryModel> response) {
                if(!response.isSuccessful()) {
                    Toast.makeText(context, "R: "+response.message(), Toast.LENGTH_LONG).show();
                    return;
                }
                GenericClassServerResponse<MyInquiryModel> genericClassServerResponse = new GenericClassServerResponse<>();
                genericClassServerResponse.setCertainGenericClassServerResponse(response.body());
                itemViewModel.selectItem(genericClassServerResponse);
            }

            @Override
            public void onFailure(Call<MyInquiryModel> call, Throwable t) {
                Toast.makeText(context, "F: "+t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }// this get the properties of the active user that was assumed by other user

}
