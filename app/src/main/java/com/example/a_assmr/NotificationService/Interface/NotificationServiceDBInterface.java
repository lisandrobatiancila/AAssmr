package com.example.a_assmr.NotificationService.Interface;

import com.example.a_assmr.CommonDir.StandardResponse;
import com.example.a_assmr.NotificationService.Model.NotificationServiceModel;
import com.example.a_assmr.NotificationService.Model.NotificationServiceModelContainer;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface NotificationServiceDBInterface {
    @FormUrlEncoded
    @POST("/api/get-active-user-notifications")
    Call<NotificationServiceModelContainer> getNotifications(@Field("userID") int userID, @Field("userEmail") String userEmail);
    @FormUrlEncoded
    @PATCH("/api/update-notification-status")
    Call<StandardResponse> updateActiveUserNotifications(@Field("notificationID") int notificationID, @Field("notificationType") String notificationType);
}
