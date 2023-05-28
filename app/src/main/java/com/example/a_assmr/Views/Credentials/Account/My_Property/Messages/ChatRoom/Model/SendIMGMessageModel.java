package com.example.a_assmr.Views.Credentials.Account.My_Property.Messages.ChatRoom.Model;

import android.content.Intent;

public class SendIMGMessageModel {
    private int userID;
    private String inboundUser, outboundUser;
    private Intent imageDATA;

    public SendIMGMessageModel(int userID, String inboundUser, String outboundUser, Intent imageDATA) {
        this.userID = userID;
        this.inboundUser = inboundUser;
        this.outboundUser = outboundUser;
        this.imageDATA = imageDATA;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getInboundUser() {
        return inboundUser;
    }

    public void setInboundUser(String inboundUser) {
        this.inboundUser = inboundUser;
    }

    public String getOutboundUser() {
        return outboundUser;
    }

    public void setOutboundUser(String outboundUser) {
        this.outboundUser = outboundUser;
    }

    public Intent getImageDATA() {
        return imageDATA;
    }

    public void setImageDATA(Intent imageDATA) {
        this.imageDATA = imageDATA;
    }
}
