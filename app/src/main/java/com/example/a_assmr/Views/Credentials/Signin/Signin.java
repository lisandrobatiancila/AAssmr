package com.example.a_assmr.Views.Credentials.Signin;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.a_assmr.R;
import com.example.a_assmr.Views.Credentials.Properties.PropertyLists.Properties;
import com.example.a_assmr.Views.Credentials.Signin.Controller.SigninController;
import com.example.a_assmr.Views.Credentials.Signin.Interface.SigninInterface;
import com.example.a_assmr.Views.Credentials.Signin.Model.SigninResponse;
import com.example.a_assmr.Views.Credentials.Signin.Model.UserCredentials;
import com.google.android.material.textfield.TextInputEditText;

public class Signin extends AppCompatActivity implements SigninInterface {
    TextInputEditText edtEmail, edtPassword;
    Button btnSignin, btnClear;
    AlertDialog dialog;
    SwipeRefreshLayout refreshLayout;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vcs_signin);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if(checkUserIsActive()) {
            Intent i_active = new Intent(this, Properties.class);
            startActivity(i_active);
        } //check if user is active?
        SigninController signinController = new SigninController(this, this);
        edtEmail = findViewById(R.id.edtEmail);
        edtPassword = findViewById(R.id.edtPassword);
        btnSignin = findViewById(R.id.btnSignin);
        btnClear = findViewById(R.id.btnClear);
        setUpProgressBar();
        btnSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = edtEmail.getText().toString();
                String password = edtPassword.getText().toString();
                UserCredentials credentials = new UserCredentials(email, password);

                signinController.signinUser(credentials);
                dialog.show();
            }
        });
        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edtEmail.setText("");edtPassword.setText("");
            }
        });
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshLayout.setRefreshing(false);
                dialog.dismiss();
            }
        });
    }
    private void setUpProgressBar() {
        View view = getLayoutInflater().inflate(R.layout.progress_bar_1, null);
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) TextView txtTitle = view.findViewById(R.id.txtProgress1Title);
        txtTitle.setText("Verifying Credentials");
        refreshLayout = view.findViewById(R.id.signupRefresh);
        AlertDialog.Builder builder = new AlertDialog.Builder(this)
                .setCancelable(false)
                .setView(view);

        dialog = builder.create();
    }
    @Override
    public void signinResponse(SigninResponse response) {
        if(response.code == 500) {
            Toast.makeText(getApplicationContext(), "Server is offline", Toast.LENGTH_SHORT).show();
        }
        else if(response.code == 401) {
            Toast.makeText(getApplicationContext(), response.message, Toast.LENGTH_SHORT).show();
        }
        else if(response.code == 406) {
            Toast.makeText(getApplicationContext(), response.message, Toast.LENGTH_SHORT).show();
        }
        else if(response.code == 200) {
            SharedPreferences preferences = getSharedPreferences("Credentials", MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString("userID", String.valueOf(response.activeUser.get(0).userID));
            editor.putString("email", response.activeUser.get(0).email);
            editor.putString("fullName", response.activeUser.get(0).fullName);
            editor.apply();

            Intent intent = new Intent(getApplicationContext(), Properties.class);
            startActivity(intent);
        }
        dialog.dismiss();
    }
    private boolean checkUserIsActive() {
        boolean userActive = false;
        SharedPreferences preferences = getSharedPreferences("Credentials", MODE_PRIVATE);
        String userID = preferences.getString("userID", "");
        if (!userID.equals(""))
            userActive = true;
        return userActive;
    }
}
