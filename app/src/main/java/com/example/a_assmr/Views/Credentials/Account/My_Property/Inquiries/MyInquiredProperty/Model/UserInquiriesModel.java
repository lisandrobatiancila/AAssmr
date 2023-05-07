package com.example.a_assmr.Views.Credentials.Account.My_Property.Inquiries.MyInquiredProperty.Model;

public class UserInquiriesModel {
    private int assumerID;
    private String userFname, userMname, userLname, userContactno, userEmail;
    private String assumptionCount, propertyStatus, image;

    public UserInquiriesModel(int assumerID, String userFname, String userMname, String userLname, String userContactno, String userEmail, String assumptionCount, String propertyStatus, String image) {
        this.assumerID = assumerID;
        this.userFname = userFname;
        this.userMname = userMname;
        this.userLname = userLname;
        this.userContactno = userContactno;
        this.userEmail = userEmail;
        this.assumptionCount = assumptionCount;
        this.propertyStatus = propertyStatus;
        this.image = image;
    }

    public int getAssumerID() {
        return assumerID;
    }

    public void setAssumerID(int assumerID) {
        this.assumerID = assumerID;
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

    public String getAssumptionCount() {
        return assumptionCount;
    }

    public void setAssumptionCount(String assumptionCount) {
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
}