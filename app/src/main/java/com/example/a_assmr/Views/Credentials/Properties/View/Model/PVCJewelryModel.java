package com.example.a_assmr.Views.Credentials.Properties.View.Model;

import java.util.List;

public class PVCJewelryModel {
    private int code, status;
    private String message;
    List<PVCJModel> jewelry;

    public PVCJewelryModel(int code, int status, String message, List<PVCJModel> jewelry) {
        this.code = code;
        this.status = status;
        this.message = message;
        this.jewelry = jewelry;
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

    public List<PVCJModel> getJewelry() {
        return jewelry;
    }

    public void setJewelry(List<PVCJModel> jewelry) {
        this.jewelry = jewelry;
    }
}
