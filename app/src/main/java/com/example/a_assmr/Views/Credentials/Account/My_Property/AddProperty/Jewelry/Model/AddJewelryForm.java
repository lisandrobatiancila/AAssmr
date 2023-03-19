package com.example.a_assmr.Views.Credentials.Account.My_Property.AddProperty.Jewelry.Model;

public class AddJewelryForm {
    String owner, cellNo, jewelryName, jewelryModel, location;
    Double downpayment, installmentpaid;
    String installmentduration, delinquent, description;

    public AddJewelryForm(String owner, String cellNo, String jewelryName, String jewelryModel, String location, Double downpayment, Double installmentpaid, String installmentduration, String delinquent, String description) {
        this.owner = owner;
        this.cellNo = cellNo;
        this.jewelryName = jewelryName;
        this.jewelryModel = jewelryModel;
        this.location = location;
        this.downpayment = downpayment;
        this.installmentpaid = installmentpaid;
        this.installmentduration = installmentduration;
        this.delinquent = delinquent;
        this.description = description;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getCellNo() {
        return cellNo;
    }

    public void setCellNo(String cellNo) {
        this.cellNo = cellNo;
    }

    public String getJewelryName() {
        return jewelryName;
    }

    public void setJewelryName(String jewelryName) {
        this.jewelryName = jewelryName;
    }

    public String getJewelryModel() {
        return jewelryModel;
    }

    public void setJewelryModel(String jewelryModel) {
        this.jewelryModel = jewelryModel;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
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

    public String getInstallmentduration() {
        return installmentduration;
    }

    public void setInstallmentduration(String installmentduration) {
        this.installmentduration = installmentduration;
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