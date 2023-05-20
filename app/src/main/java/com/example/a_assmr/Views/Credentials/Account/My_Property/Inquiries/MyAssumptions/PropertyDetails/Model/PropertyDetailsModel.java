package com.example.a_assmr.Views.Credentials.Account.My_Property.Inquiries.MyAssumptions.PropertyDetails.Model;

import java.util.List;

public class PropertyDetailsModel {
    int status, code;
    List<PropertyModel> certainProperty;

    public PropertyDetailsModel(int status, int code, List<PropertyModel> certainProperty) {
        this.status = status;
        this.code = code;
        this.certainProperty = certainProperty;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public List<PropertyModel> getCertainProperty() {
        return certainProperty;
    }

    public void setCertainProperty(List<PropertyModel> certainProperty) {
        this.certainProperty = certainProperty;
    }
}