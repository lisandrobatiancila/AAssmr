package com.example.a_assmr.Views.Credentials.Properties.Assume.Interface;

import com.example.a_assmr.Views.Credentials.Properties.Assume.Model.AssumptionFormModel;
import com.example.a_assmr.Views.Credentials.Properties.Assume.Model.AssumptionParentModel;
import com.example.a_assmr.Views.Credentials.Properties.Assume.Model.AssumptionServerResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface AssumptionDBInterface {
    @GET("/api/get-assumer-information/{userID}")
    Call<AssumptionParentModel> getAssumerInformation(@Path("userID") int userID);

    @POST("/api/process-assumption-request")
    Call<AssumptionServerResponse> submitAssumerInformation(@Body AssumptionFormModel assumptionFormModel);
}
