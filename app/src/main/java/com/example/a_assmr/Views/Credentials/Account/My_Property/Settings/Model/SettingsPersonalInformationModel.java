package com.example.a_assmr.Views.Credentials.Account.My_Property.Settings.Model;

public class SettingsPersonalInformationModel {
    int userID;
    String firstname, middlename, lastname, contactno;
    String address;

    public SettingsPersonalInformationModel(int userID, String firstname, String middlename, String lastname, String contactno, String address) {
        this.userID = userID;
        this.firstname = firstname;
        this.middlename = middlename;
        this.lastname = lastname;
        this.contactno = contactno;
        this.address = address;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getMiddlename() {
        return middlename;
    }

    public void setMiddlename(String middlename) {
        this.middlename = middlename;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getContactno() {
        return contactno;
    }

    public void setContactno(String contactno) {
        this.contactno = contactno;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
