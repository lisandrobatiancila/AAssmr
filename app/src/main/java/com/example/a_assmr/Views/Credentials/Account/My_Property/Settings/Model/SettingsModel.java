package com.example.a_assmr.Views.Credentials.Account.My_Property.Settings.Model;

import com.example.a_assmr.CommonDir.StandardResponse;
import com.example.a_assmr.Views.Credentials.Signup.Model.Cities;

import java.util.List;

public class SettingsModel {
    private List<SettingsInformation> userSettingsInformation;
    private StandardResponse response;
    private List<Cities> address;

    public SettingsModel(List<SettingsInformation> userSettingsInformation, StandardResponse response, List<Cities> address) {
        this.userSettingsInformation = userSettingsInformation;
        this.response = response;
        this.address = address;
    }

    public List<SettingsInformation> getUserSettingsInformation() {
        return userSettingsInformation;
    }

    public void setUserSettingsInformation(List<SettingsInformation> userSettingsInformation) {
        this.userSettingsInformation = userSettingsInformation;
    }

    public StandardResponse getResponse() {
        return response;
    }

    public void setResponse(StandardResponse response) {
        this.response = response;
    }

    public List<Cities> getAddress() {
        return address;
    }

    public void setAddress(List<Cities> address) {
        this.address = address;
    }
}
