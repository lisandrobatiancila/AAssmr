package com.example.a_assmr.CommonDir;

import android.content.Context;
import android.content.SharedPreferences;

public class ActiveUserSharedPref {
    Context context;

    public ActiveUserSharedPref(Context context) {
        this.context = context;
    }

    public int activeUserID() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("Credentials", Context.MODE_PRIVATE);
        int userID = 0;
        if(!sharedPreferences.getString("userID", "").equals(""))
            userID = Integer.parseInt(sharedPreferences.getString("userID", ""));

        return userID;
    }
    public String activeUserEmail() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("Credentials", Context.MODE_PRIVATE);
        String userEmail = sharedPreferences.getString("email", "");
        return userEmail;
    }
    public String activeUserFullName() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("Credentials", Context.MODE_PRIVATE);
        String userFullName = sharedPreferences.getString("fullName", "");

        return userFullName;
    }
    public void clearUserSession() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("Credentials", Context.MODE_PRIVATE);
        sharedPreferences.edit().remove("userID").commit();
        sharedPreferences.edit().remove("email").commit();
        sharedPreferences.edit().remove("fullName").commit();
    }
}
