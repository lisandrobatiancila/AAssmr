package com.example.a_assmr.Views.Credentials.Account.My_Property.AddProperty.Realestate.Model;

public class AddRealestateForm {
    String owner, mobileNo, location, installmentduration;
    Double downpayment, installmentpaid;
    String delinquent, realestateType, description;

    public AddRealestateForm(String owner, String mobileNo, String location, String installmentduration, Double downpayment, Double installmentpaid, String delinquent, String realestateType, String description) {
        this.owner = owner;
        this.mobileNo = mobileNo;
        this.location = location;
        this.installmentduration = installmentduration;
        this.downpayment = downpayment;
        this.installmentpaid = installmentpaid;
        this.delinquent = delinquent;
        this.realestateType = realestateType;
        this.description = description;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getInstallmentduration() {
        return installmentduration;
    }

    public void setInstallmentduration(String installmentduration) {
        this.installmentduration = installmentduration;
    }

    public Double getDownpayment() {
        return downpayment;
    }

    public void setDownpayment(Double downpayment) {
        this.downpayment = downpayment;
    }

    public Double getInstallmentpaid() {
        return installmentpaid;
    }

    public void setInstallmentpaid(Double installmentpaid) {
        this.installmentpaid = installmentpaid;
    }

    public String getDelinquent() {
        return delinquent;
    }

    public void setDelinquent(String delinquent) {
        this.delinquent = delinquent;
    }

    public String getRealestateType() {
        return realestateType;
    }

    public void setRealestateType(String realestateType) {
        this.realestateType = realestateType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
