package com.example.a_assmr.Views.Credentials.Account.My_Property.Inquiries.MyInquiredProperty.Model;

import java.util.List;

public class MyInquiryModel {
    private int code, status;
    private String message;
    private List<UserInquiriesModel> inquiries;

    public MyInquiryModel(int code, int status, String message, List<UserInquiriesModel> inquiries) {
        this.code = code;
        this.status = status;
        this.message = message;
        this.inquiries = inquiries;
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

    public List<UserInquiriesModel> getInquiries() {
        return inquiries;
    }

    public void setInquiries(List<UserInquiriesModel> inquiries) {
        this.inquiries = inquiries;
    }
}