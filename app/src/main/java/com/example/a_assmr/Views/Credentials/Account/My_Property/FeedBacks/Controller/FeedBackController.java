package com.example.a_assmr.Views.Credentials.Account.My_Property.FeedBacks.Controller;

import android.content.Context;
import android.widget.Toast;

import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.a_assmr.Common;
import com.example.a_assmr.CommonDir.GenericClassServerResponse;
import com.example.a_assmr.CommonDir.StandardResponse;
import com.example.a_assmr.Views.Credentials.Account.My_Property.FeedBacks.Interface.FeedBackDBInterface;
import com.example.a_assmr.Views.Credentials.Account.My_Property.FeedBacks.Model.FeedBackForm;
import com.example.a_assmr.Views.Credentials.Account.My_Property.FeedBacks.Model.FeedBackServerResponse;
import com.example.a_assmr.Views.Credentials.Account.My_Property.ViewProperty.ItemViewModel;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FeedBackController {
    Common common;
    Context context;
    ItemViewModel itemViewModel;
    public FeedBackController(Context context, FragmentActivity fragmentActivity) {
        this.common = new Common();
        this.context = context;
        this.itemViewModel = new ViewModelProvider(fragmentActivity).get(ItemViewModel.class);
    }
    public void createFeedBack(FeedBackForm feedBackForm) {
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

        FeedBackDBInterface dbInterface = retrofit.create(FeedBackDBInterface.class);
        Call<StandardResponse> call = dbInterface.addNewFeedBack(feedBackForm);
        call.enqueue(new Callback<StandardResponse>() {
            @Override
            public void onResponse(Call<StandardResponse> call, Response<StandardResponse> response) {
                if(!response.isSuccessful()) {
                    Toast.makeText(context, "R: "+response.message(), Toast.LENGTH_LONG).show();
                    return;
                }
                GenericClassServerResponse<StandardResponse> genericClass = new GenericClassServerResponse<>();
                genericClass.setCertainGenericClassServerResponse(response.body());

                itemViewModel.selectItem(genericClass);
            }

            @Override
            public void onFailure(Call<StandardResponse> call, Throwable t) {
                Toast.makeText(context, "F: "+t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
    public void getAllFeedBack() {
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
        FeedBackDBInterface feedBackDBInterface = retrofit.create(FeedBackDBInterface.class);
        Call<FeedBackServerResponse> call = feedBackDBInterface.getAllFeedBacks();
        call.enqueue(new Callback<FeedBackServerResponse>() {
            @Override
            public void onResponse(Call<FeedBackServerResponse> call, Response<FeedBackServerResponse> response) {
                if(!response.isSuccessful()) {
                    Toast.makeText(context, "R: "+response.message(), Toast.LENGTH_LONG).show();
                    return;
                }
                GenericClassServerResponse<FeedBackServerResponse> genericClassServerResponse = new GenericClassServerResponse<>();
                genericClassServerResponse.setCertainGenericClassServerResponse(response.body());

                itemViewModel.selectItem(genericClassServerResponse);
            }

            @Override
            public void onFailure(Call<FeedBackServerResponse> call, Throwable t) {
                Toast.makeText(context, "F: "+t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}
