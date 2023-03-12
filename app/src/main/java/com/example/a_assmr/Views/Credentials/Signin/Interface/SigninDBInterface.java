package com.example.a_assmr.Views.Credentials.Signin.Interface;

import com.example.a_assmr.Views.Credentials.Signin.Model.SigninResponse;
import com.example.a_assmr.Views.Credentials.Signin.Model.UserCredentials;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface SigninDBInterface {
    @POST("/api/mobile-signin")
    Call <SigninResponse> userSignin(@Body UserCredentials credentials);
}
