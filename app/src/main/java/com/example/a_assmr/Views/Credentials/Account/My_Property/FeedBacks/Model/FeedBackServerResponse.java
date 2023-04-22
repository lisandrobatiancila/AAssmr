package com.example.a_assmr.Views.Credentials.Account.My_Property.FeedBacks.Model;

import java.util.List;

public class FeedBackServerResponse {
    private int code, status;
    private String message;
    private List<FeedBackModel> userFeedBacks;

    public FeedBackServerResponse(int code, int status, String message, List<FeedBackModel> userFeedBacks) {
        this.code = code;
        this.status = status;
        this.message = message;
        this.userFeedBacks = userFeedBacks;
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

    public List<FeedBackModel> getUserFeedBacks() {
        return userFeedBacks;
    }

    public void setUserFeedBacks(List<FeedBackModel> userFeedBacks) {
        this.userFeedBacks = userFeedBacks;
    }
}
