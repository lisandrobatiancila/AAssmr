package com.example.a_assmr.Views.Credentials.Account.My_Property.Settings.Interface;

import com.example.a_assmr.CommonDir.StandardResponse;
import com.example.a_assmr.Views.Credentials.Account.My_Property.Settings.Model.SettingsAccountInformationModel;
import com.example.a_assmr.Views.Credentials.Account.My_Property.Settings.Model.SettingsModel;
import com.example.a_assmr.Views.Credentials.Account.My_Property.Settings.Model.SettingsPersonalInformationModel;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface SettingsDBInterface {
    @GET("/api/mobile-settings/{userID}")
    Call<SettingsModel> getSettingsInformation(@Path("userID") int userID);
    @POST("/api/mobile-settings/updatePI") // PI means Personal Information
    Call<StandardResponse> updatePersonalInformation(@Body SettingsPersonalInformationModel personalInformationModel);
    @POST("/api/mobile-settings/updateAI")
    Call<StandardResponse> updateAccountInformation(@Body SettingsAccountInformationModel accountInformationModel);
}
