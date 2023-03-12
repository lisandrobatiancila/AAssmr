package com.example.a_assmr.Views.Credentials.Signup;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.example.a_assmr.Common;
import com.example.a_assmr.Views.Credentials.Signup.Interface.SignupDBInterface;
import com.example.a_assmr.Views.Credentials.Signup.Interface.SignupInterface;
import com.example.a_assmr.Views.Credentials.Signup.Model.Cities;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SignupSpinnerModel extends BroadcastReceiver {
    Common common;
    Context context;
    SignupInterface signupInterface;
    public SignupSpinnerModel(Context context, SignupInterface signupInterface) {
        this.context = context;
        this.common = new Common();
        this.signupInterface = signupInterface;
    }
    public void getAddresess() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(common.getApiURI())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        SignupDBInterface signupDBInterface = retrofit.create(SignupDBInterface.class);
        Call<List<Cities>> call =signupDBInterface.getAllCities();

        call.enqueue(new Callback<List<Cities>>() {
            @Override
            public void onResponse(Call<List<Cities>> call, Response<List<Cities>> response) {
                if(!response.isSuccessful()) {
                    Toast.makeText(context, "R: "+response.message().toString(), Toast.LENGTH_LONG).show();
                    return;
                }
                signupInterface.getAddressLists(response.body());
            }

            @Override
            public void onFailure(Call<List<Cities>> call, Throwable t) {
                Toast.makeText(context, "F: "+t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    public List<String> getGenderLists() {
        List<String> gender = new ArrayList<>();
        gender.add("Male");
        gender.add("Female");

        return gender;
    }
    @Override
    public void onReceive(Context context, Intent intent) {
        String d = intent.getAction();

        Toast.makeText(context, "Received: "+d, Toast.LENGTH_LONG).show();
    }
}
