package com.example.a_assmr.Views.Credentials.Account.My_Property.Settings.Model;

public class SettingsAccountInformationModel {
    private int userID;
    private String email, password;

    public SettingsAccountInformationModel(int userID, String email, String password) {
        this.userID = userID;
        this.email = email;
        this.password = password;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
