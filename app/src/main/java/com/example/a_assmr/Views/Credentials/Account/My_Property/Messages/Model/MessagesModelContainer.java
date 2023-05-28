package com.example.a_assmr.Views.Credentials.Account.My_Property.Messages.Model;

import java.util.List;

public class MessagesModelContainer {
    private int code, status;
    private String message;

    private List<MessagesModel> messagesModelList;

    public MessagesModelContainer(int code, int status, String message, List<MessagesModel> messagesModelList) {
        this.code = code;
        this.status = status;
        this.message = message;
        this.messagesModelList = messagesModelList;
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

    public List<MessagesModel> getMessagesModelList() {
        return messagesModelList;
    }

    public void setMessagesModelList(List<MessagesModel> messagesModelList) {
        this.messagesModelList = messagesModelList;
    }
}
