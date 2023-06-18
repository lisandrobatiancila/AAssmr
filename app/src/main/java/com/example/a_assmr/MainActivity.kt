package com.example.a_assmr

import android.annotation.SuppressLint
import android.content.ClipData.Item
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
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
    var alert: AlertDialog? = null
    var common: Common? = null
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.index_view)
        val s = NotificationService(applicationContext)

        PromptEnterIP()
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
            startActivity(i_active) // this redirect the user if he's already logged in
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

    fun PromptEnterIP() {
        common = Common()
        var dialog = AlertDialog.Builder(this)
        val inflater = LayoutInflater.from(this)
        val v: View = inflater.inflate(R.layout.prompt_ip, null, false)
        dialog.setView(v)

        alert = dialog.create()

        alert?.setTitle("Mesasge")
        alert?.setCancelable(false)

        var txtIP: EditText = v.findViewById(R.id.txtIP)
        var btnOk: Button = v.findViewById(R.id.btnOk)
        btnOk.setOnClickListener {
            val ip: String = txtIP.text.toString()
            CommonIP.ip = "http://${ip}" // unused right now
            common?.setCurrIP(ip)
            alert?.dismiss()
        }
        alert?.show()

    }

    override fun onDestroy() {
        super.onDestroy()
        alert?.dismiss()
    }
}