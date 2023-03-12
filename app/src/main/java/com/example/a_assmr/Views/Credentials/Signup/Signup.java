package com.example.a_assmr.Views.Credentials.Signup;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.a_assmr.R;
import com.example.a_assmr.Views.Credentials.Signup.Controller.SignupController;
import com.example.a_assmr.Views.Credentials.Signup.Interface.SignupInterface;
import com.example.a_assmr.Views.Credentials.Signup.Model.Barangays;
import com.example.a_assmr.Views.Credentials.Signup.Model.Cities;
import com.example.a_assmr.Views.Credentials.Signup.Model.Provinces;
import com.example.a_assmr.Views.Credentials.Signup.Model.SignupServerResponse;
import com.example.a_assmr.Views.Credentials.Signup.Model.SingupForm;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Signup extends AppCompatActivity implements SignupInterface{
    EditText edtMiddlename ,edtLastname, edtPhone, edtEmail, edtPassword;
    TextInputEditText edtFirstname;
    Button btnSignup, btnReset;
    Spinner spinnGender, spinnProvince, spinnCity, spinBarangay;
    HashMap<String, List<Provinces>> hashedCities = new HashMap<>();
    HashMap<String, List<String>> hashedProvinces = new HashMap<>();
    List<String> provinces = new ArrayList<>();
    List<String> barangay = new ArrayList<>();
    String strCity = "cebu", strProvince = "bohol", strBarangay = "albuquerque";
    String gender = "Male";
    SignupSpinnerModel signupSpinnerModel;
    SignupController signupController;
    AlertDialog dialog;
    LinearLayout backarrow;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vcs_signup);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        signupSpinnerModel = new SignupSpinnerModel(this, this);
        signupController = new SignupController(this, this);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        setUpProgressBar();

        edtFirstname = findViewById(R.id.editFirstname);
        edtMiddlename = findViewById(R.id.edtMiddlename);
        edtLastname = findViewById(R.id.edtLastname);
        edtPhone = findViewById(R.id.edtPhone);
        edtEmail = findViewById(R.id.edtEmail);
        edtPassword = findViewById(R.id.edtPassword);

        spinnGender = findViewById(R.id.spinGender);
        spinnCity = findViewById(R.id.spinCity);
        spinnProvince = findViewById(R.id.spinProvince);
        spinBarangay = findViewById(R.id.spinBarangay);

        btnSignup = findViewById(R.id.btnSignup);
        btnReset = findViewById(R.id.btnReset);

        spinnGender.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, signupSpinnerModel.getGenderLists()));
        signupSpinnerModel.getAddresess();

        spinnGender.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                gender = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        spinnCity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                strCity = adapterView.getItemAtPosition(i).toString();
                provinces.clear();
                hashedProvinces.clear();

                for(Provinces p: hashedCities.get(strCity)) {
                    provinces.add(p.name);
                    hashedProvinces.put(p.name, p.barangay);
                }
                spinnProvince.setAdapter(new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item, provinces));
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        spinnProvince.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                strProvince = adapterView.getItemAtPosition(i).toString();
                barangay.clear();
                for(String b: hashedProvinces.get(strProvince)) {
                    barangay.add(b);
                }
                spinBarangay.setAdapter(new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item, barangay));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        spinBarangay.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                strBarangay = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String firstname = edtFirstname.getText().toString();
                String middlename = edtMiddlename.getText().toString();
                String lastname = edtLastname.getText().toString();
                String phoneNo = edtPhone.getText().toString();
                String email = edtEmail.getText().toString();
                String password = edtPassword.getText().toString();

                SingupForm form = new SingupForm(firstname, middlename, lastname, gender,
                        phoneNo, strCity, strProvince, strBarangay, email, password);

                dialog.show();
                signupController.createUserCredentials(form);
            }
        });
        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edtFirstname.setText("");edtMiddlename.setText("");edtLastname.setText("");edtPhone.setText("");
                edtEmail.setText("");edtPassword.setText("");
            }
        });
    }
    private void setUpProgressBar() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view = getLayoutInflater().inflate(R.layout.progress_bar_1, null);
        SwipeRefreshLayout refreshLayout = view.findViewById(R.id.signupRefresh);

        builder.setView(view)
               .setCancelable(false);
        dialog = builder.create();

        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshLayout.setRefreshing(false);
                dialog.dismiss();
            }
        });
    }
    @Override
    public void getAddressLists(List<Cities> cities) {
        List<String> cityLists = new ArrayList<>();
        for(Cities c: cities) {
            cityLists.add(c.city);

            hashedCities.put(c.city, c.provinces); // used for accessing certain provinces for each cities
        }
        spinnCity.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, cityLists));

        provinces.clear();
        for(Provinces p: hashedCities.get("cebu")) {
            provinces.add(p.name);
            hashedProvinces.put(p.name, p.barangay);
        }
        spinnProvince.setAdapter(new ArrayAdapter(this, android.R.layout.simple_list_item_1, provinces));

        barangay.clear();
        for(String b: hashedProvinces.get("bohol")) {
            barangay.add(b);
        }
        spinBarangay.setAdapter(new ArrayAdapter(this, android.R.layout.simple_list_item_1, barangay));
    }

    @Override
    public void createNewUser(SignupServerResponse createNewUser) {
        dialog.dismiss();
        Toast.makeText(this, createNewUser.message, Toast.LENGTH_LONG).show();
    }

}