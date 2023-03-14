package com.example.a_assmr.Views.Credentials.Account.My_Property.AddProperty.Vehicle.Interface;

import com.example.a_assmr.Views.Credentials.Account.My_Property.AddProperty.Vehicle.Model.VehicleResponse;

import java.util.Map;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PartMap;

public interface AddVehicleDBInterface {
    @Multipart
    @POST("/api/upload-vehicle-property")
    Call<VehicleResponse> uploadVehicleProperty(@PartMap() Map<String, MultipartBody.Part> partMap);
}

