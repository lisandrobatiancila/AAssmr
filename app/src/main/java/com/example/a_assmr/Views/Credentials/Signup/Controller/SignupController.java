package com.example.a_assmr.Views.Credentials.Signup.Controller;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.example.a_assmr.Common;
import com.example.a_assmr.CommonIP;
import com.example.a_assmr.Views.Credentials.Signup.Interface.SignupDBInterface;
import com.example.a_assmr.Views.Credentials.Signup.Interface.SignupInterface;
import com.example.a_assmr.Views.Credentials.Signup.Model.SignupServerResponse;
import com.example.a_assmr.Views.Credentials.Signup.Model.SingupForm;

import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SignupController extends BroadcastReceiver {
    Context context;
    Common common;
    SignupInterface signupInterface;
    public SignupController(Context context, SignupInterface signupInterface) {
        this.context = context;
        this.common = new Common();
        this.signupInterface = signupInterface;
    }
    public void createUserCredentials(SingupForm singupForm) {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient httpClient = new OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(CommonIP.ip)
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient)
                .build();
        SignupDBInterface dbInterface = retrofit.create(SignupDBInterface.class);
        Call <SignupServerResponse> call = dbInterface.signUpUser(singupForm);

        call.enqueue(new Callback<SignupServerResponse>() {
            @Override
            public void onResponse(Call<SignupServerResponse> call, Response<SignupServerResponse> response) {
                if(!response.isSuccessful()) {
                    Toast.makeText(context, "R: "+response.message(), Toast.LENGTH_LONG).show();
                    return;
                }
                signupInterface.createNewUser(response.body());
            }

            @Override
            public void onFailure(Call<SignupServerResponse> call, Throwable t) {
                Toast.makeText(context, "F: "+t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void onReceive(Context context, Intent intent) {
//        this works if you want to click each item; ex: in a recyclerview or a list I THINK -_-
    }
}
