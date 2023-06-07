package com.example.a_assmr.NotificationService.Model;

import java.util.List;

public class NotificationServiceModelContainer {
    private int code, status;
    private String message;
    private List<NotificationServiceModel> notificationServiceModelList;

    public NotificationServiceModelContainer(int code, int status, String message, List<NotificationServiceModel> notificationServiceModelList) {
        this.code = code;
        this.status = status;
        this.message = message;
        this.notificationServiceModelList = notificationServiceModelList;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<NotificationServiceModel> getNotificationServiceModelList() {
        return notificationServiceModelList;
    }

    public void setNotificationServiceModelList(List<NotificationServiceModel> notificationServiceModelList) {
        this.notificationServiceModelList = notificationServiceModelList;
    }
}
