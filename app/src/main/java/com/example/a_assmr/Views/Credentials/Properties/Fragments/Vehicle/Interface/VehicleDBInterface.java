package com.example.a_assmr.Views.Credentials.Properties.Fragments.Vehicle.Interface;

import com.example.a_assmr.Views.Credentials.Properties.Fragments.Vehicle.VehicleModel.VehicleServerRequest;
import com.example.a_assmr.Views.Credentials.Properties.Fragments.Vehicle.VehicleModel.VehicleServerResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface VehicleDBInterface {
    @GET("/api/vehicle-property-lists/{propStartID}")
    Call <VehicleServerResponse> vehicleLists(@Path("propStartID") int propStartID);
}
