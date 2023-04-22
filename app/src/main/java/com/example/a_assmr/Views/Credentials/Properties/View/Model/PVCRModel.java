package com.example.a_assmr.Views.Credentials.Properties.View.Model;

public class PVCRModel {
    public int realestateID, propertyID;
    public String realestateOwner, realestateContactno, realestateLocation, realestateDownpayment;
    public String realestateInstallmentpaid, realestateInstallmentduration, realestateDelinquent;
    public String realestateType, realestateDescription;
    public String propertyStatus, realestateIMG;
    public int assumptionCount;

    public PVCRModel(int realestateID, int propertyID, String realestateOwner, String realestateContactno, String realestateLocation, String realestateDownpayment, String realestateInstallmentpaid, String realestateInstallmentduration, String realestateDelinquent, String realestateType, String realestateDescription, String propertyStatus, String realestateIMG, int assumptionCount) {
        this.realestateID = realestateID;
        this.propertyID = propertyID;
        this.realestateOwner = realestateOwner;
        this.realestateContactno = realestateContactno;
        this.realestateLocation = realestateLocation;
        this.realestateDownpayment = realestateDownpayment;
        this.realestateInstallmentpaid = realestateInstallmentpaid;
        this.realestateInstallmentduration = realestateInstallmentduration;
        this.realestateDelinquent = realestateDelinquent;
        this.realestateType = realestateType;
        this.realestateDescription = realestateDescription;
        this.propertyStatus = propertyStatus;
        this.realestateIMG = realestateIMG;
        this.assumptionCount = assumptionCount;
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

    public String getRealestateDownpayment() {
        return realestateDownpayment;
    }

    public void setRealestateDownpayment(String realestateDownpayment) {
        this.realestateDownpayment = realestateDownpayment;
    }

    public String getRealestateInstallmentpaid() {
        return realestateInstallmentpaid;
    }

    public void setRealestateInstallmentpaid(String realestateInstallmentpaid) {
        this.realestateInstallmentpaid = realestateInstallmentpaid;
    }

    public String getRealestateInstallmentduration() {
        return realestateInstallmentduration;
    }

    public void setRealestateInstallmentduration(String realestateInstallmentduration) {
        this.realestateInstallmentduration = realestateInstallmentduration;
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

    public int getAssumptionCount() {
        return assumptionCount;
    }

    public void setAssumptionCount(int assumptionCount) {
        this.assumptionCount = assumptionCount;
    }
}
