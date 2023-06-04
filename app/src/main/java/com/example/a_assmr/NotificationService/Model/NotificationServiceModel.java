package com.example.a_assmr.NotificationService.Model;

public class NotificationServiceModel {
    private String notificationTitle, notificationContent;

    public NotificationServiceModel(String notificationTitle, String notificationContent) {
        this.notificationTitle = notificationTitle;
        this.notificationContent = notificationContent;
    }

    public String getNotificationTitle() {
        return notificationTitle;
    }

    public void setNotificationTitle(String notificationTitle) {
        this.notificationTitle = notificationTitle;
    }

    public String getNotificationContent() {
        return notificationContent;
    }

    public void setNotificationContent(String notificationContent) {
        this.notificationContent = notificationContent;
    }
}
