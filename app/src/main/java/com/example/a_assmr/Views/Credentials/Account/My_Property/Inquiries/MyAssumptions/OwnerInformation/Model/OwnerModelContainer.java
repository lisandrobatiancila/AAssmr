package com.example.a_assmr.Views.Credentials.Account.My_Property.Inquiries.MyAssumptions.OwnerInformation.Model;

import java.util.List;

public class OwnerModelContainer {
    private int code, status;
    private String message;
    List<OwnerModel> ownerInformation;

    public OwnerModelContainer(int code, int status, String message, List<OwnerModel> ownerInformation) {
        this.code = code;
        this.status = status;
        this.message = message;
        this.ownerInformation = ownerInformation;
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

    public List<OwnerModel> getOwnerInformation() {
        return ownerInformation;
    }

    public void setOwnerInformation(List<OwnerModel> ownerInformation) {
        this.ownerInformation = ownerInformation;
    }
}
