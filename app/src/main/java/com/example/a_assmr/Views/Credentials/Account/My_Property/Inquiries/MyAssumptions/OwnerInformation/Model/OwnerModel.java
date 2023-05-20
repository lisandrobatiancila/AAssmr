package com.example.a_assmr.Views.Credentials.Account.My_Property.Inquiries.MyAssumptions.OwnerInformation.Model;

public class OwnerModel {
    private String ownerName, ownerAddress, ownerContactno;
    private String ownerEmail;

    public OwnerModel(String ownerName, String ownerAddress, String ownerContactno, String ownerEmail) {
        this.ownerName = ownerName;
        this.ownerAddress = ownerAddress;
        this.ownerContactno = ownerContactno;
        this.ownerEmail = ownerEmail;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getOwnerAddress() {
        return ownerAddress;
    }

    public void setOwnerAddress(String ownerAddress) {
        this.ownerAddress = ownerAddress;
    }

    public String getOwnerContactno() {
        return ownerContactno;
    }

    public void setOwnerContactno(String ownerContactno) {
        this.ownerContactno = ownerContactno;
    }

    public String getOwnerEmail() {
        return ownerEmail;
    }

    public void setOwnerEmail(String ownerEmail) {
        this.ownerEmail = ownerEmail;
    }
}
