package com.example.a_assmr.Views.Credentials.Signup.Interface;

import com.example.a_assmr.Views.Credentials.Signup.Model.Cities;
import com.example.a_assmr.Views.Credentials.Signup.Model.SignupServerResponse;
import com.example.a_assmr.Views.Credentials.Signup.Model.SingupForm;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface SignupDBInterface {
    @GET("/api/mobile-signup")
    Call <List<Cities>> getAllCities();
    @POST("/api/mobile-signup")
    Call <SignupServerResponse> signUpUser(@Body SingupForm singupForm);
}
