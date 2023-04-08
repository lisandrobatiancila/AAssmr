package com.example.a_assmr.Views.Credentials.Account.My_Property.ViewProperty.VURCertainProperty.Model;

public class UpdateRealestateModel {
    int propertyID;
    String owner, contactno, location, downpayment, paid, duration, delinquent, description;

    public UpdateRealestateModel(int propertyID, String owner, String contactno, String location, String downpayment, String paid, String duration, String delinquent, String description) {
        this.propertyID = propertyID;
        this.owner = owner;
        this.contactno = contactno;
        this.location = location;
        this.downpayment = downpayment;
        this.paid = paid;
        this.duration = duration;
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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDownpayment() {
        return downpayment;
    }

    public void setDownpayment(String downpayment) {
        this.downpayment = downpayment;
    }

    public String getPaid() {
        return paid;
    }

    public void setPaid(String paid) {
        this.paid = paid;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
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
