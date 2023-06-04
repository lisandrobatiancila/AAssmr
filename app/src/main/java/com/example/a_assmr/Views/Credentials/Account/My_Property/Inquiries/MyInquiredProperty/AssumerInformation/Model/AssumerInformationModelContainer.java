package com.example.a_assmr.Views.Credentials.Account.My_Property.Inquiries.MyInquiredProperty.AssumerInformation.Model;

import java.util.List;

public class AssumerInformationModelContainer {
    private int code, status;
    private String message;
    private List<AssumerInformationModel> assumerLists;

    public AssumerInformationModelContainer(int code, int status, String message, List<AssumerInformationModel> assumerLists) {
        this.code = code;
        this.status = status;
        this.message = message;
        this.assumerLists = assumerLists;
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

    public List<AssumerInformationModel> getAssumerLists() {
        return assumerLists;
    }

    public void setAssumerLists(List<AssumerInformationModel> assumerLists) {
        this.assumerLists = assumerLists;
    }
}