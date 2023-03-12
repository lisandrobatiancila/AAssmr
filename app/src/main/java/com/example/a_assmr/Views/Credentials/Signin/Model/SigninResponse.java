package com.example.a_assmr.Views.Credentials.Signin.Model;

import com.example.a_assmr.CommonDir.ActiveUser;

import java.util.List;

public class SigninResponse {
    public int code;
    public String message;
    public int status;
    public List<ActiveUser> activeUser;
}
// holds the server responses
