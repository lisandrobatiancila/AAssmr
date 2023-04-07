package com.example.a_assmr.Views.Credentials.Account.My_Property.ViewProperty.Realestate.Model;

public class MyRealestateLists {
    public int realestateID, propertyID;
    public String realestateOwner, realestateContactno, realestateLocation;
    public Double realestateDownpayment, realestateInstallmentpaid;
    public String realestatInstallmentduration, realestateDelinquent, realestateType, realestateDescription;
    public int propertyCount;
    public String propertyStatus, realestateIMG;

    public MyRealestateLists(int realestateID, int propertyID, String realestateOwner, String realestateContactno, String realestateLocation, Double realestateDownpayment, Double realestateInstallmentpaid, String realestatInstallmentduration, String realestateDelinquent, String realestateType, String realestateDescription, int propertyCount, String propertyStatus, String realestateIMG) {
        this.realestateID = realestateID;
        this.propertyID = propertyID;
        this.realestateOwner = realestateOwner;
        this.realestateContactno = realestateContactno;
        this.realestateLocation = realestateLocation;
        this.realestateDownpayment = realestateDownpayment;
        this.realestateInstallmentpaid = realestateInstallmentpaid;
        this.realestatInstallmentduration = realestatInstallmentduration;
        this.realestateDelinquent = realestateDelinquent;
        this.realestateType = realestateType;
        this.realestateDescription = realestateDescription;
        this.propertyCount = propertyCount;
        this.propertyStatus = propertyStatus;
        this.realestateIMG = realestateIMG;
    }

    public int getRealestateID() {
        return realestateID;
    }

    public void setRealestateID(int realestateID) {
        this.realestateID = realestateID;
    }

    public int getPropertyID() {
        return propertyID;
    }

    public void setPropertyID(int propertyID) {
        this.propertyID = propertyID;
    }

    public String getRealestateOwner() {
        return realestateOwner;
    }

    public void setRealestateOwner(String realestateOwner) {
        this.realestateOwner = realestateOwner;
    }

    public String getRealestateContactno() {
        return realestateContactno;
    }

    public void setRealestateContactno(String realestateContactno) {
        this.realestateContactno = realestateContactno;
    }

    public String getRealestateLocation() {
        return realestateLocation;
    }

    public void setRealestateLocation(String realestateLocation) {
        this.realestateLocation = realestateLocation;
    }

    public Double getRealestateDownpayment() {
        return realestateDownpayment;
    }

    public void setRealestateDownpayment(Double realestateDownpayment) {
        this.realestateDownpayment = realestateDownpayment;
    }

    public Double getRealestateInstallmentpaid() {
        return realestateInstallmentpaid;
    }

    public void setRealestateInstallmentpaid(Double realestateInstallmentpaid) {
        this.realestateInstallmentpaid = realestateInstallmentpaid;
    }

    public String getRealestatInstallmentduration() {
        return realestatInstallmentduration;
    }

    public void setRealestatInstallmentduration(String realestatInstallmentduration) {
        this.realestatInstallmentduration = realestatInstallmentduration;
    }

    public String getRealestateDelinquent() {
        return realestateDelinquent;
    }

    public void setRealestateDelinquent(String realestateDelinquent) {
        this.realestateDelinquent = realestateDelinquent;
    }

    public String getRealestateType() {
        return realestateType;
    }

    public void setRealestateType(String realestateType) {
        this.realestateType = realestateType;
    }

    public String getRealestateDescription() {
        return realestateDescription;
    }

    public void setRealestateDescription(String realestateDescription) {
        this.realestateDescription = realestateDescription;
    }

    public int getPropertyCount() {
        return propertyCount;
    }

    public void setPropertyCount(int propertyCount) {
        this.propertyCount = propertyCount;
    }

    public String getPropertyStatus() {
        return propertyStatus;
    }

    public void setPropertyStatus(String propertyStatus) {
        this.propertyStatus = propertyStatus;
    }

    public String getRealestateIMG() {
        return realestateIMG;
    }

    public void setRealestateIMG(String realestateIMG) {
        this.realestateIMG = realestateIMG;
    }
}