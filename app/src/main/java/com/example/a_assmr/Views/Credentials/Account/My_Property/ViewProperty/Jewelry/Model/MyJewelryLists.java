package com.example.a_assmr.Views.Credentials.Account.My_Property.ViewProperty.Jewelry.Model;

public class MyJewelryLists {
    public int jewelryID, propertyID;
    public String jewelryOwner, jewelryName, jewelryModel, propertyStatus;
    public int assumptionCount;
    String jewelryIMG;

    public MyJewelryLists(int jewelryID, int propertyID, String jewelryOwner, String jewelryName, String jewelryModel, String propertyStatus, int assumptionCount, String jewelryIMG) {
        this.jewelryID = jewelryID;
        this.propertyID = propertyID;
        this.jewelryOwner = jewelryOwner;
        this.jewelryName = jewelryName;
        this.jewelryModel = jewelryModel;
        this.propertyStatus = propertyStatus;
        this.assumptionCount = assumptionCount;
        this.jewelryIMG = jewelryIMG;
    }

    public int getJewelryID() {
        return jewelryID;
    }

    public void setJewelryID(int jewelryID) {
        this.jewelryID = jewelryID;
    }

    public int getPropertyID() {
        return propertyID;
    }

    public void setPropertyID(int propertyID) {
        this.propertyID = propertyID;
    }

    public String getJewelryOwner() {
        return jewelryOwner;
    }

    public void setJewelryOwner(String jewelryOwner) {
        this.jewelryOwner = jewelryOwner;
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

    public String getPropertyStatus() {
        return propertyStatus;
    }

    public void setPropertyStatus(String propertyStatus) {
        this.propertyStatus = propertyStatus;
    }

    public int getAssumptionCount() {
        return assumptionCount;
    }

    public void setAssumptionCount(int assumptionCount) {
        this.assumptionCount = assumptionCount;
    }

    public String getJewelryIMG() {
        return jewelryIMG;
    }

    public void setJewelryIMG(String jewelryIMG) {
        this.jewelryIMG = jewelryIMG;
    }
}
