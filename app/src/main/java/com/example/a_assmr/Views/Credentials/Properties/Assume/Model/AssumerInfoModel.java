package com.example.a_assmr.Views.Credentials.Properties.Assume.Model;

public class AssumerInfoModel {
    String userFname, userMname, userLname, userAddress, userContactno;

    public AssumerInfoModel(String userFname, String userMname, String userLname, String userAddress, String userContactno) {
        this.userFname = userFname;
        this.userMname = userMname;
        this.userLname = userLname;
        this.userAddress = userAddress;
        this.userContactno = userContactno;
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
}
