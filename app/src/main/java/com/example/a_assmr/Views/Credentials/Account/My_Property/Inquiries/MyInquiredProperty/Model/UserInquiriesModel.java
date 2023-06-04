package com.example.a_assmr.Views.Credentials.Account.My_Property.Inquiries.MyInquiredProperty.Model;

public class UserInquiriesModel {
    private int propertyID, assumerID, itemID;
    private String info1, info2;
    private int assumptionCount;
    private String propertyStatus, image, type;

    public UserInquiriesModel(int propertyID, int assumerID, int itemID, String info1, String info2, int assumptionCount, String propertyStatus, String image, String type) {
        this.propertyID = propertyID;
        this.assumerID = assumerID;
        this.itemID = itemID;
        this.info1 = info1;
        this.info2 = info2;
        this.assumptionCount = assumptionCount;
        this.propertyStatus = propertyStatus;
        this.image = image;
        this.type = type;
    }

    public int getPropertyID() {
        return propertyID;
    }

    public void setPropertyID(int propertyID) {
        this.propertyID = propertyID;
    }

    public int getAssumerID() {
        return assumerID;
    }

    public void setAssumerID(int assumerID) {
        this.assumerID = assumerID;
    }

    public int getItemID() {
        return itemID;
    }

    public void setItemID(int itemID) {
        this.itemID = itemID;
    }

    public String getInfo1() {
        return info1;
    }

    public void setInfo1(String info1) {
        this.info1 = info1;
    }

    public String getInfo2() {
        return info2;
    }

    public void setInfo2(String info2) {
        this.info2 = info2;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}