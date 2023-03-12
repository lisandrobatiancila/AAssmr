package com.example.a_assmr.Views.Credentials.Signup.Interface;

import com.example.a_assmr.Common;
import com.example.a_assmr.Views.Credentials.Signup.Model.Cities;
import com.example.a_assmr.Views.Credentials.Signup.Model.SignupServerResponse;

import java.util.List;

public interface SignupInterface {
    public void getAddressLists(List<Cities> cities);
    public void createNewUser(SignupServerResponse createNewUser);
}
