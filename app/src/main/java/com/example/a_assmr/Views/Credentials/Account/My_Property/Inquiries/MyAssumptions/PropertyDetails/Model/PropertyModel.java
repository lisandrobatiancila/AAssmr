package com.example.a_assmr.Views.Credentials.Account.My_Property.Inquiries.MyAssumptions.PropertyDetails.Model;

public class PropertyModel {
    int propertyID, itemID;
    String owner, contactno, location, downpayment, duration, delinquent, propertyType, description, img;
    int assumptionCount;

    public PropertyModel(int propertyID, int itemID, String owner, String contactno, String location, String downpayment, String duration, String delinquent, String propertyType, String description, String img, int assumptionCount) {
        this.propertyID = propertyID;
        this.itemID = itemID;
        this.owner = owner;
        this.contactno = contactno;
        this.location = location;
        this.downpayment = downpayment;
        this.duration = duration;
        this.delinquent = delinquent;
        this.propertyType = propertyType;
        this.description = description;
        this.img = img;
        this.assumptionCount = assumptionCount;
    }

    public int getPropertyID() {
        return propertyID;
    }

    public void setPropertyID(int propertyID) {
        this.propertyID = propertyID;
    }

    public int getItemID() {
        return itemID;
    }

    public void setItemID(int itemID) {
        this.itemID = itemID;
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

    public String getPropertyType() {
        return propertyType;
    }

    public void setPropertyType(String propertyType) {
        this.propertyType = propertyType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public int getAssumptionCount() {
        return assumptionCount;
    }

    public void setAssumptionCount(int assumptionCount) {
        this.assumptionCount = assumptionCount;
    }
}