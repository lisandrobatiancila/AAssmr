package com.example.a_assmr.Views.Credentials.Properties.PropertyLists.Vehicle.Interface;

import com.example.a_assmr.Views.Credentials.Properties.PropertyLists.Vehicle.VehicleModel.VehicleServerResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface VehicleDBInterface {
    @GET("/api/vehicle-property-lists/{propStartID}")
    Call <VehicleServerResponse> vehicleLists(@Path("propStartID") int propStartID);
}
