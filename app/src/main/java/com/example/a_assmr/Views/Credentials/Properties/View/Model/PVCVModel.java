package com.example.a_assmr.Views.Credentials.Properties.View.Model;

public class PVCVModel {
    public int vehicleID, propertyID;
    public String vehicleOwner, vehicleContactno, vehicleBrand, vehicleModel, vehicleLocation;
    public Double vehicleDownpayment, vehicleInstallmentPaid;
    public String vehicleInstallmentDuration, vehicleDelinquent, description;

    public int assumptionCount;

    public String propertyStatus, vehicleIMAGES;

    public PVCVModel(int vehicleID, int propertyID, String vehicleOwner, String vehicleContactno, String vehicleBrand, String vehicleModel, String vehicleLocation, Double vehicleDownpayment, Double vehicleInstallmentPaid, String vehicleInstallmentDuration, String vehicleDelinquent, String description, int assumptionCount, String propertyStatus, String vehicleIMAGES) {
        this.vehicleID = vehicleID;
        this.propertyID = propertyID;
        this.vehicleOwner = vehicleOwner;
        this.vehicleContactno = vehicleContactno;
        this.vehicleBrand = vehicleBrand;
        this.vehicleModel = vehicleModel;
        this.vehicleLocation = vehicleLocation;
        this.vehicleDownpayment = vehicleDownpayment;
        this.vehicleInstallmentPaid = vehicleInstallmentPaid;
        this.vehicleInstallmentDuration = vehicleInstallmentDuration;
        this.vehicleDelinquent = vehicleDelinquent;
        this.description = description;
        this.assumptionCount = assumptionCount;
        this.propertyStatus = propertyStatus;
        this.vehicleIMAGES = vehicleIMAGES;
    }

    public int getVehicleID() {
        return vehicleID;
    }

    public void setVehicleID(int vehicleID) {
        this.vehicleID = vehicleID;
    }

    public int getPropertyID() {
        return propertyID;
    }

    public void setPropertyID(int propertyID) {
        this.propertyID = propertyID;
    }

    public String getVehicleOwner() {
        return vehicleOwner;
    }

    public void setVehicleOwner(String vehicleOwner) {
        this.vehicleOwner = vehicleOwner;
    }

    public String getVehicleContactno() {
        return vehicleContactno;
    }

    public void setVehicleContactno(String vehicleContactno) {
        this.vehicleContactno = vehicleContactno;
    }

    public String getVehicleBrand() {
        return vehicleBrand;
    }

    public void setVehicleBrand(String vehicleBrand) {
        this.vehicleBrand = vehicleBrand;
    }

    public String getVehicleModel() {
        return vehicleModel;
    }

    public void setVehicleModel(String vehicleModel) {
        this.vehicleModel = vehicleModel;
    }

    public String getVehicleLocation() {
        return vehicleLocation;
    }

    public void setVehicleLocation(String vehicleLocation) {
        this.vehicleLocation = vehicleLocation;
    }

    public Double getVehicleDownpayment() {
        return vehicleDownpayment;
    }

    public void setVehicleDownpayment(Double vehicleDownpayment) {
        this.vehicleDownpayment = vehicleDownpayment;
    }

    public Double getVehicleInstallmentPaid() {
        return vehicleInstallmentPaid;
    }

    public void setVehicleInstallmentPaid(Double vehicleInstallmentPaid) {
        this.vehicleInstallmentPaid = vehicleInstallmentPaid;
    }

    public String getVehicleInstallmentDuration() {
        return vehicleInstallmentDuration;
    }

    public void setVehicleInstallmentDuration(String vehicleInstallmentDuration) {
        this.vehicleInstallmentDuration = vehicleInstallmentDuration;
    }

    public String getVehicleDelinquent() {
        return vehicleDelinquent;
    }

    public void setVehicleDelinquent(String vehicleDelinquent) {
        this.vehicleDelinquent = vehicleDelinquent;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getAssumptionCount() {
        return assumptionCount;
    }

    public void setAssumptionCount(int assumptionCount) {
        this.assumptionCount = assumptionCount;
    }

    public String getPropertyStatus() {
        return propertyStatus;
    }

    public void setPropertyStatus(String propertyStatus) {
        this.propertyStatus = propertyStatus;
    }

    public String getVehicleIMAGES() {
        return vehicleIMAGES;
    }

    public void setVehicleIMAGES(String vehicleIMAGES) {
        this.vehicleIMAGES = vehicleIMAGES;
    }
}
