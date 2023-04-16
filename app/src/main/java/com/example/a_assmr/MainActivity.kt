package com.example.a_assmr

import android.annotation.SuppressLint
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.a_assmr.Views.Credentials.Properties.PropertyLists.Properties
import com.example.a_assmr.Views.Credentials.Signin.Signin
import com.example.a_assmr.Views.Credentials.Signup.Signup

class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.index_view)

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