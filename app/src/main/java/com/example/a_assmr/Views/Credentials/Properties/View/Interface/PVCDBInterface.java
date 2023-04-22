package com.example.a_assmr.Views.Credentials.Properties.View.Interface;

import com.example.a_assmr.Views.Credentials.Properties.View.Model.PVCJewelryModel;
import com.example.a_assmr.Views.Credentials.Properties.View.Model.PVCRealestateModel;
import com.example.a_assmr.Views.Credentials.Properties.View.Model.PVCVehicleModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface PVCDBInterface {
    // means PropertyViewCertainVehicleDBInterface
    @GET("/api/get-certain-vehicle/{propertyID}")
    Call<PVCVehicleModel> getCertainVehicle(@Path("propertyID") int propertyID);
    @GET("/api/get-certain-realestate/{propertyID}")
    Call<PVCRealestateModel> getCertainRealestate(@Path("propertyID") int propertyID);

    @GET("/api/get-certain-jewelry/{propertyID}")
    Call<PVCJewelryModel> getCertainJewelry(@Path("propertyID") int propertyID);
}
