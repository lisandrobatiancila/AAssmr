package com.example.a_assmr.Views.Credentials.Properties.Assume.Controller;

import android.content.Context;
import android.widget.Toast;

import com.example.a_assmr.Common;
import com.example.a_assmr.CommonDir.GenericClass;
import com.example.a_assmr.Views.Credentials.Properties.Assume.Interface.AssumptionDBInterface;
import com.example.a_assmr.Views.Credentials.Properties.Assume.Interface.AssumptionInterface;
import com.example.a_assmr.Views.Credentials.Properties.Assume.Model.AssumptionFormModel;
import com.example.a_assmr.Views.Credentials.Properties.Assume.Model.AssumptionParentModel;
import com.example.a_assmr.Views.Credentials.Properties.Assume.Model.AssumptionServerResponse;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AssumptionController {
    Context context;
    Common common;
    AssumptionInterface assumptionInterface;

    public AssumptionController(Context context, AssumptionInterface assumptionInterface) {
        this.context = context;
        this.assumptionInterface = assumptionInterface;
        this.common = new Common();
    }
    public void getAssummerInformation(int userID) {
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

        AssumptionDBInterface dbInterface = retrofit.create(AssumptionDBInterface.class);
        Call<AssumptionParentModel> call = dbInterface.getAssumerInformation(userID);

        call.enqueue(new Callback<AssumptionParentModel>() {
            @Override
            public void onResponse(Call<AssumptionParentModel> call, Response<AssumptionParentModel> response) {
                if(!response.isSuccessful()) {
                    Toast.makeText(context, "R: "+response.message(), Toast.LENGTH_LONG).show();
                    return;
                }
                GenericClass<AssumptionParentModel> genericClass = new GenericClass<>();
                genericClass.setCertainClass(response.body());
                assumptionInterface.AssumptionResponses(genericClass);
            }

            @Override
            public void onFailure(Call<AssumptionParentModel> call, Throwable t) {
                Toast.makeText(context, "F: "+t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
    public void submitAssumptionForm(AssumptionFormModel assumptionFormModel) {
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

        AssumptionDBInterface assumptionDBInterface = retrofit.create(AssumptionDBInterface.class);
        Call<AssumptionServerResponse> call = assumptionDBInterface.submitAssumerInformation(assumptionFormModel);
        call.enqueue(new Callback<AssumptionServerResponse>() {
            @Override
            public void onResponse(Call<AssumptionServerResponse> call, Response<AssumptionServerResponse> response) {
                if(!response.isSuccessful()) {
                    Toast.makeText(context, "R: "+response.message(), Toast.LENGTH_LONG).show();
                    return;
                }
                GenericClass<AssumptionServerResponse> genericClass = new GenericClass<>();
                genericClass.setCertainClass(response.body());
                assumptionInterface.AssumptionResponses(genericClass);
            }

            @Override
            public void onFailure(Call<AssumptionServerResponse> call, Throwable t) {
                Toast.makeText(context, "F: "+t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}
