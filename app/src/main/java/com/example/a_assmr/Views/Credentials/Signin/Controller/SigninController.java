package com.example.a_assmr.Views.Credentials.Signin.Controller;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.example.a_assmr.Common;
import com.example.a_assmr.Views.Credentials.Signin.Interface.SigninDBInterface;
import com.example.a_assmr.Views.Credentials.Signin.Interface.SigninInterface;
import com.example.a_assmr.Views.Credentials.Signin.Model.SigninResponse;
import com.example.a_assmr.Views.Credentials.Signin.Model.UserCredentials;
import com.example.a_assmr.Views.Credentials.Signin.Signin;
import com.example.a_assmr.Views.Credentials.Signup.Interface.SignupDBInterface;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SigninController extends BroadcastReceiver {
    Context context;
    SigninInterface signinInterface;
    Common common;
    public SigninController(Context context, SigninInterface signinInterface) {
        this.context = context;
        this.signinInterface = signinInterface;
        common = new Common();
    }
    public void signinUser(UserCredentials userCredentials) {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(common.getApiURI())
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();

        SigninDBInterface dbInterface = retrofit.create(SigninDBInterface.class);
        Call<SigninResponse> call = dbInterface.userSignin(userCredentials);
        call.enqueue(new Callback<SigninResponse>() {
            @Override
            public void onResponse(Call<SigninResponse> call, Response<SigninResponse> response) {
                if(!response.isSuccessful()) {
                    Toast.makeText(context, "R: "+response.message(), Toast.LENGTH_LONG).show();
                    return;
                }
                signinInterface.signinResponse(response.body());
            }

            @Override
            public void onFailure(Call<SigninResponse> call, Throwable t) {
                Toast.makeText(context, "F: "+t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
    @Override
    public void onReceive(Context context, Intent intent) {

    }
}
