package com.example.a_assmr.Views.Credentials.Account.My_Property.Messages.ChatRoom.Model;

import java.util.List;

public class GetMessageModelContainer {
    private int code, status;
    private String message;
    private List<GetMessagesModel> userMessages;

    public GetMessageModelContainer(int code, int status, String message, List<GetMessagesModel> userMessages) {
        this.code = code;
        this.status = status;
        this.message = message;
        this.userMessages = userMessages;
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

    public List<GetMessagesModel> getUserMessages() {
        return userMessages;
    }

    public void setUserMessages(List<GetMessagesModel> userMessages) {
        this.userMessages = userMessages;
    }
}
