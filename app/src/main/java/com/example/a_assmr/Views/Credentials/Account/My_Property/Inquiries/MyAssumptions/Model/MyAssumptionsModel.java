package com.example.a_assmr.Views.Credentials.Account.My_Property.Inquiries.MyAssumptions.Model;

import java.util.List;

public class MyAssumptionsModel {
    private int code, status;
    private String message;
    private List<InquiriesAssumptionModel> myassumptions;

    public MyAssumptionsModel(int code, int status, String message, List<InquiriesAssumptionModel> myassumptions) {
        this.code = code;
        this.status = status;
        this.message = message;
        this.myassumptions = myassumptions;
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

    public List<InquiriesAssumptionModel> getMyassumptions() {
        return myassumptions;
    }

    public void setMyassumptions(List<InquiriesAssumptionModel> myassumptions) {
        this.myassumptions = myassumptions;
    }
}
