package com.example.a_assmr.Views.Credentials.Account.My_Property.AddProperty.Vehicle.Model;

public class AddVehicleForm {
    String owner, mobileNo;
    String brand, model, location;
    double downpayment, installmentPaid;
    String installmentDuration;
    String delinquent, description;

    public AddVehicleForm(String owner, String mobileNo, String brand, String model, String location, double downpayment, double installmentPaid, String installmentDuration, String delinquent, String description) {
        this.owner = owner;
        this.mobileNo = mobileNo;
        this.brand = brand;
        this.model = model;
        this.location = location;
        this.downpayment = downpayment;
        this.installmentPaid = installmentPaid;
        this.installmentDuration = installmentDuration;
        this.delinquent = delinquent;
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

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public double getDownpayment() {
        return downpayment;
    }

    public void setDownpayment(double downpayment) {
        this.downpayment = downpayment;
    }

    public double getInstallmentPaid() {
        return installmentPaid;
    }

    public void setInstallmentPaid(double installmentPaid) {
        this.installmentPaid = installmentPaid;
    }

    public String getInstallmentDuration() {
        return installmentDuration;
    }

    public void setInstallmentDuration(String installmentDuration) {
        this.installmentDuration = installmentDuration;
    }

    public String getDelinquent() {
        return delinquent;
    }

    public void setDelinquent(String delinquent) {
        this.delinquent = delinquent;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}