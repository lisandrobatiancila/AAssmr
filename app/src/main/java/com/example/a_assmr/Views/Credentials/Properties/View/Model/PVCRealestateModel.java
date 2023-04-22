package com.example.a_assmr.Views.Credentials.Properties.View.Model;

import java.util.List;

public class PVCRealestateModel {
    int code, status;
    String message;
    public List<PVCRModel> realestate;

    public PVCRealestateModel(int code, int status, String message, List<PVCRModel> realestate) {
        this.code = code;
        this.status = status;
        this.message = message;
        this.realestate = realestate;
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

    public List<PVCRModel> getRealestate() {
        return realestate;
    }

    public void setRealestate(List<PVCRModel> realestate) {
        this.realestate = realestate;
    }
}
