package com.example.a_assmr.Views.Credentials.Properties.PropertyLists.Jewelry.JewelryInterface;

import com.example.a_assmr.Views.Credentials.Properties.PropertyLists.Jewelry.JewelryModel.JewelryServerResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface JewelryDBInterface {
    @GET("/api/jewelries-properry-lists/{propStartID}")
    Call <JewelryServerResponse> getJewelryLists(@Path("propStartID") int propStartID);
}
