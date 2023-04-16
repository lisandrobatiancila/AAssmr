package com.example.a_assmr.Views.Credentials.Properties.PropertyLists.Realestate.RealestateInterface;

import com.example.a_assmr.Views.Credentials.Properties.PropertyLists.Realestate.RealestateModel.RealestateServerResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface RealestateDBInterface {
    @GET("/api/realestate-property-lists/{propStartID}")
    Call <RealestateServerResponse> realestateLists(@Path("propStartID") int propStartID);
}
