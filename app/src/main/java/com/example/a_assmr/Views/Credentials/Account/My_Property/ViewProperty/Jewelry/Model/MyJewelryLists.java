package com.example.a_assmr.Views.Credentials.Account.My_Property.ViewProperty.Jewelry.Model;

public class MyJewelryLists {
    int jewelryID, propertyID;
    String jewelryOwner, jewelryContactno, jewelryName, jewelryModel, jewelryLocation, jewelryDownpayment;
    String jewelryInstallmentpaid, jewelryInstallmentduration, jewelryDelinquent, jewelryDescription;
    String propertyStatus;
    int assumptionCount;
    String jewelryIMG;

    public MyJewelryLists(int jewelryID, int propertyID, String jewelryOwner, String jewelryContactno, String jewelryName, String jewelryModel, String jewelryLocation, String jewelryDownpayment, String jewelryInstallmentpaid, String jewelryInstallmentduration, String jewelryDelinquent, String jewelryDescription, String propertyStatus, int assumptionCount, String jewelryIMG) {
        this.jewelryID = jewelryID;
        this.propertyID = propertyID;
        this.jewelryOwner = jewelryOwner;
        this.jewelryContactno = jewelryContactno;
        this.jewelryName = jewelryName;
        this.jewelryModel = jewelryModel;
        this.jewelryLocation = jewelryLocation;
        this.jewelryDownpayment = jewelryDownpayment;
        this.jewelryInstallmentpaid = jewelryInstallmentpaid;
        this.jewelryInstallmentduration = jewelryInstallmentduration;
        this.jewelryDelinquent = jewelryDelinquent;
        this.jewelryDescription = jewelryDescription;
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

    public String getJewelryContactno() {
        return jewelryContactno;
    }

    public void setJewelryContactno(String jewelryContactno) {
        this.jewelryContactno = jewelryContactno;
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

    public String getJewelryLocation() {
        return jewelryLocation;
    }

    public void setJewelryLocation(String jewelryLocation) {
        this.jewelryLocation = jewelryLocation;
    }

    public String getJewelryDownpayment() {
        return jewelryDownpayment;
    }

    public void setJewelryDownpayment(String jewelryDownpayment) {
        this.jewelryDownpayment = jewelryDownpayment;
    }

    public String getJewelryInstallmentpaid() {
        return jewelryInstallmentpaid;
    }

    public void setJewelryInstallmentpaid(String jewelryInstallmentpaid) {
        this.jewelryInstallmentpaid = jewelryInstallmentpaid;
    }

    public String getJewelryInstallmentduration() {
        return jewelryInstallmentduration;
    }

    public void setJewelryInstallmentduration(String jewelryInstallmentduration) {
        this.jewelryInstallmentduration = jewelryInstallmentduration;
    }

    public String getJewelryDelinquent() {
        return jewelryDelinquent;
    }

    public void setJewelryDelinquent(String jewelryDelinquent) {
        this.jewelryDelinquent = jewelryDelinquent;
    }

    public String getJewelryDescription() {
        return jewelryDescription;
    }

    public void setJewelryDescription(String jewelryDescription) {
        this.jewelryDescription = jewelryDescription;
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