package com.example.a_assmr.Views.Credentials.Account.My_Property.Inquiries.MyAssumptions.Model;

public class InquiriesAssumptionModel {
    private String info1, info2, info3;
    private String info4, info5;

    public InquiriesAssumptionModel(String info1, String info2, String info3, String info4, String info5) {
        this.info1 = info1; // owner-name
        this.info2 = info2; // property info generic
        this.info3 = info3; // property info generic
        this.info4 = info4; // image
        this.info5 = info5; // property type 'vehicle', 'jewelry', 'realestate'
    }

    public String getInfo1() {
        return info1;
    }

    public void setInfo1(String info1) {
        this.info1 = info1;
    }

    public String getInfo2() {
        return info2;
    }

    public void setInfo2(String info2) {
        this.info2 = info2;
    }

    public String getInfo3() {
        return info3;
    }

    public void setInfo3(String info3) {
        this.info3 = info3;
    }

    public String getInfo4() {
        return info4;
    }

    public void setInfo4(String info4) {
        this.info4 = info4;
    }

    public String getInfo5() {
        return info5;
    }

    public void setInfo5(String info5) {
        this.info5 = info5;
    }
}
