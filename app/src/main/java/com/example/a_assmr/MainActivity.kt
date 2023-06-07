package com.example.a_assmr

import android.annotation.SuppressLint
import android.content.ClipData.Item
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.a_assmr.CommonDir.ActiveUserSharedPref
import com.example.a_assmr.CommonDir.GenericClassServerResponse
import com.example.a_assmr.NotificationService.Model.NotificationServiceModelContainer
import com.example.a_assmr.NotificationService.NotificationService
import com.example.a_assmr.Views.Credentials.Account.My_Property.ViewProperty.ItemViewModel
import com.example.a_assmr.Views.Credentials.Properties.PropertyLists.Properties
import com.example.a_assmr.Views.Credentials.Signin.Signin
import com.example.a_assmr.Views.Credentials.Signup.Signup
import java.util.Timer
import java.util.TimerTask

class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.index_view)
        val s = NotificationService(applicationContext)
        Timer().schedule(object: TimerTask() {
            override fun run() {
                val ausp = ActiveUserSharedPref(applicationContext)
                if((!ausp.activeUserID().equals(null) || !ausp.activeUserID().equals("")) ||
                    (!ausp.activeUserEmail().equals(null) || !ausp.activeUserEmail().equals(""))) {
                    s.pushNotifications(ausp.activeUserID(), ausp.activeUserEmail())
                }
            }
        }, 0, 10000)

        if(checkUserIsActive()) {
            var i_active = Intent(this, Properties::class.java)
            startActivity(i_active)
        }
        var btnSignup = findViewById<Button>(R.id.btnSignup)
        var btnSignin = findViewById<Button>(R.id.btnSignin)

        btnSignup.setOnClickListener {
            var i_signup = Intent(applicationContext, Signup::class.java)
            startActivity(i_signup)
        }
        btnSignin.setOnClickListener {
            var i_signin = Intent(this, Signin::class.java)
            startActivity(i_signin)
        }
    }
    fun checkUserIsActive(): Boolean {
        var userActive: Boolean = false;
        var preferences: SharedPreferences = getSharedPreferences("Credentials", MODE_PRIVATE)
        val userID: String? = preferences.getString("userID", "")
        if(userID != "")
            userActive = true
        return userActive
    }
}