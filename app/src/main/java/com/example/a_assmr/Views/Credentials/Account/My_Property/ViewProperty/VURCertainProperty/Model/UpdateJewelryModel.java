package com.example.a_assmr.Views.Credentials.Account.My_Property.ViewProperty.VURCertainProperty.Model;

public class UpdateJewelryModel {
    int propertyID;
    String owner, contactno, jname, jmodel, location, installmentpaid, installmentduration, downpayment, delinquent;
    String description;

    public UpdateJewelryModel(int propertyID, String owner, String contactno, String jname, String jmodel, String location, String installmentpaid, String installmentduration, String downpayment, String delinquent, String description) {
        this.propertyID = propertyID;
        this.owner = owner;
        this.contactno = contactno;
        this.jname = jname;
        this.jmodel = jmodel;
        this.location = location;
        this.installmentpaid = installmentpaid;
        this.installmentduration = installmentduration;
        this.downpayment = downpayment;
        this.delinquent = delinquent;
        this.description = description;
    }

    public int getPropertyID() {
        return propertyID;
    }

    public void setPropertyID(int propertyID) {
        this.propertyID = propertyID;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getContactno() {
        return contactno;
    }

    public void setContactno(String contactno) {
        this.contactno = contactno;
    }

    public String getJname() {
        return jname;
    }

    public void setJname(String jname) {
        this.jname = jname;
    }

    public String getJmodel() {
        return jmodel;
    }

    public void setJmodel(String jmodel) {
        this.jmodel = jmodel;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getInstallmentpaid() {
        return installmentpaid;
    }

    public void setInstallmentpaid(String installmentpaid) {
        this.installmentpaid = installmentpaid;
    }

    public String getInstallmentduration() {
        return installmentduration;
    }

    public void setInstallmentduration(String installmentduration) {
        this.installmentduration = installmentduration;
    }

    public String getDownpayment() {
        return downpayment;
    }

    public void setDownpayment(String downpayment) {
        this.downpayment = downpayment;
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
