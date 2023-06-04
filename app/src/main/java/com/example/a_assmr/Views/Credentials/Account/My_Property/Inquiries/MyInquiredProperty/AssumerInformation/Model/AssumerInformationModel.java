package com.example.a_assmr.Views.Credentials.Account.My_Property.Inquiries.MyInquiredProperty.AssumerInformation.Model;

public class AssumerInformationModel {
    private int TotalAssumer, assumerID, propertyID, userID;
    private String assumer_address, assumer_income, assumer_work;
    private String transaction_date, userEmail, fullName;

    public AssumerInformationModel(int totalAssumer, int assumerID, int propertyID, int userID, String assumer_address, String assumer_income, String assumer_work, String transaction_date, String userEmail, String fullName) {
        TotalAssumer = totalAssumer;
        this.assumerID = assumerID;
        this.propertyID = propertyID;
        this.userID = userID;
        this.assumer_address = assumer_address;
        this.assumer_income = assumer_income;
        this.assumer_work = assumer_work;
        this.transaction_date = transaction_date;
        this.userEmail = userEmail;
        this.fullName = fullName;
    }

    public int getTotalAssumer() {
        return TotalAssumer;
    }

    public void setTotalAssumer(int totalAssumer) {
        TotalAssumer = totalAssumer;
    }

    public int getAssumerID() {
        return assumerID;
    }

    public void setAssumerID(int assumerID) {
        this.assumerID = assumerID;
    }

    public int getPropertyID() {
        return propertyID;
    }

    public void setPropertyID(int propertyID) {
        this.propertyID = propertyID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getAssumer_address() {
        return assumer_address;
    }

    public void setAssumer_address(String assumer_address) {
        this.assumer_address = assumer_address;
    }

    public String getAssumer_income() {
        return assumer_income;
    }

    public void setAssumer_income(String assumer_income) {
        this.assumer_income = assumer_income;
    }

    public String getAssumer_work() {
        return assumer_work;
    }

    public void setAssumer_work(String assumer_work) {
        this.assumer_work = assumer_work;
    }

    public String getTransaction_date() {
        return transaction_date;
    }

    public void setTransaction_date(String transaction_date) {
        this.transaction_date = transaction_date;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}
