package com.example.a_assmr.Views.Credentials.Account.My_Property.Settings.Model;

public class SettingsInformation {
    private int userID;
    private String userFname, userMname, userLname, userAddress, userContactno;
    private String userEmail, account_key;

    public SettingsInformation(int userID, String userFname, String userMname, String userLname, String userAddress, String userContactno, String userEmail, String account_key) {
        this.userID = userID;
        this.userFname = userFname;
        this.userMname = userMname;
        this.userLname = userLname;
        this.userAddress = userAddress;
        this.userContactno = userContactno;
        this.userEmail = userEmail;
        this.account_key = account_key;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getUserFname() {
        return userFname;
    }

    public void setUserFname(String userFname) {
        this.userFname = userFname;
    }

    public String getUserMname() {
        return userMname;
    }

    public void setUserMname(String userMname) {
        this.userMname = userMname;
    }

    public String getUserLname() {
        return userLname;
    }

    public void setUserLname(String userLname) {
        this.userLname = userLname;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public String getUserContactno() {
        return userContactno;
    }

    public void setUserContactno(String userContactno) {
        this.userContactno = userContactno;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getAccount_key() {
        return account_key;
    }

    public void setAccount_key(String account_key) {
        this.account_key = account_key;
    }
} // a generic class
