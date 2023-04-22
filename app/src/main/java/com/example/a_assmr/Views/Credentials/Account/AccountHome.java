package com.example.a_assmr.Views.Credentials.Account;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.a_assmr.CommonDir.ActiveUserSharedPref;
import com.example.a_assmr.CommonDir.GenericClass;
import com.example.a_assmr.CommonDir.GenericClassInterface;
import com.example.a_assmr.R;
import com.example.a_assmr.Views.Credentials.Account.My_Property.AddProperty.AddProperty;
import com.example.a_assmr.Views.Credentials.Account.My_Property.AddProperty.Jewelry.AddJewelryProperty;
import com.example.a_assmr.Views.Credentials.Account.My_Property.AddProperty.Jewelry.Interface.AddJewelryInterface;
import com.example.a_assmr.Views.Credentials.Account.My_Property.AddProperty.Jewelry.Model.AddJewelryResponse;
import com.example.a_assmr.Views.Credentials.Account.My_Property.AddProperty.Realestate.AddRealestateProperty;
import com.example.a_assmr.Views.Credentials.Account.My_Property.AddProperty.Realestate.Interface.AddRealesateInterface;
import com.example.a_assmr.Views.Credentials.Account.My_Property.AddProperty.Realestate.Model.AddRealestateResponse;
import com.example.a_assmr.Views.Credentials.Account.My_Property.AddProperty.Vehicle.AddVehicleProperty;
import com.example.a_assmr.Views.Credentials.Account.My_Property.AddProperty.Vehicle.Interface.AddVehicleInterface;
import com.example.a_assmr.Views.Credentials.Account.My_Property.AddProperty.Vehicle.Model.AddVehicleResponse;
import com.example.a_assmr.Views.Credentials.Account.My_Property.FeedBacks.FeedBackFragment;
import com.example.a_assmr.Views.Credentials.Account.My_Property.Settings.SettingsFragment;
import com.example.a_assmr.Views.Credentials.Account.My_Property.ViewProperty.ViewProperty;
import com.example.a_assmr.Views.Credentials.Properties.PropertyLists.Properties;
import com.example.a_assmr.Views.Credentials.Signin.Signin;
import com.google.android.material.navigation.NavigationView;

public class AccountHome extends AppCompatActivity implements AddVehicleInterface, AddRealesateInterface, AddJewelryInterface, GenericClassInterface {
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ActionBarDrawerToggle toggle;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.account_home);
        setTitle("");
        drawerLayout = findViewById(R.id.accntHome_drawer_layout);
        navigationView = findViewById(R.id.accntHomeNavView);
        toggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.navigation_open, R.string.navigation_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setActiveUserInformation(navigationView);

        getSupportFragmentManager().beginTransaction().replace(R.id.accntFrameLayout, new ViewProperty()).commit();
        navigationView.setCheckedItem(R.id.accntViewProperty); // set as active by default
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.accntDashboard:
                        Intent i_go_assume_prop = new Intent(getApplicationContext(), Properties.class);
                        startActivity(i_go_assume_prop);
                    break;
                    case R.id.accntAddProperty:
                        getSupportFragmentManager().beginTransaction().replace(R.id.accntFrameLayout, new AddProperty()).commit();
                        drawerLayout.closeDrawer(GravityCompat.START);
                    break;
                    case R.id.accntViewProperty:
                        getSupportFragmentManager().beginTransaction().replace(R.id.accntFrameLayout, new ViewProperty()).commit();
                        drawerLayout.closeDrawer(GravityCompat.START);
                    break;
                    case R.id.accntFeedbacks:
                        getSupportFragmentManager().beginTransaction().replace(R.id.accntFrameLayout, new FeedBackFragment()).commit();
                        drawerLayout.closeDrawer(GravityCompat.START);
                    break;
                    case R.id.accntMessages:
                    break;
                    case R.id.accntInquiries:
                    break;
                    case R.id.accntSettings:
                        getSupportFragmentManager().beginTransaction().replace(R.id.accntFrameLayout, new SettingsFragment()).commit();
                        drawerLayout.closeDrawer(GravityCompat.START);
                    break;
                    case R.id.accntLogout:
                        try {
                            ActiveUserSharedPref sharedPref = new ActiveUserSharedPref(AccountHome.this);
                            sharedPref.clearUserSession();
                            int userID = sharedPref.activeUserID(); // throws an exception; its fine for now but need to DEBUG this
                        }
                        catch (Exception e) {
                            Intent i_signin = new Intent(getApplicationContext(), Signin.class);
                            AccountHome.this.startActivity(i_signin);
                        }
                    break;
                }
                return true;
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        toggle.onOptionsItemSelected(item);
        return true;
    }

    @Override
    public void addVehicleProperty(AddVehicleResponse vehicleResponse) {
        if(vehicleResponse.code == 200) {
            Toast.makeText(this, vehicleResponse.message, Toast.LENGTH_LONG).show();
        }
        else
            Toast.makeText(this, vehicleResponse.message, Toast.LENGTH_LONG).show();
    } // it has two process 1) save the vehicle info; 2) upload and save the vehicle image(s)
    @Override
    public void addRealestateProperty(AddRealestateResponse addRealestateResponse) {
        if(addRealestateResponse.code == 200) {
            Toast.makeText(this, addRealestateResponse.message, Toast.LENGTH_LONG).show();
        }
        else
            Toast.makeText(this, addRealestateResponse.message, Toast.LENGTH_LONG).show();
    } // it has two process 1) save the realestate info; 2) upload and save the realestate image(s)
    @Override
    public void resetForm(GenericClass genericClass) {
        Object obj = genericClass.getCertainClass();
        if(obj instanceof AddVehicleProperty) {
            AddRealestateProperty addVehicleProp = (AddRealestateProperty) genericClass.getCertainClass();
            addVehicleProp.resetForm();
        }
        else if(obj instanceof  AddRealestateProperty) {
            AddRealestateProperty addRealProp = (AddRealestateProperty) genericClass.getCertainClass();
            addRealProp.resetForm();
        }
        else if(obj instanceof AddJewelryProperty) {
            AddJewelryProperty addJewelProp = (AddJewelryProperty) genericClass.getCertainClass();
            addJewelProp.resetForm();
        }
    }
    private void setActiveUserInformation(NavigationView navigationView) {
        View view = navigationView.getHeaderView(0);

        TextView txtUserFullName = view.findViewById(R.id.txtAccntHomeName);
        TextView txtUserEmail = view.findViewById(R.id.txtAccntHomeEmial);

        ActiveUserSharedPref sharedPref = new ActiveUserSharedPref(this);

        txtUserFullName.setText(sharedPref.activeUserFullName());
        txtUserEmail.setText(sharedPref.activeUserEmail());
    }

    @Override
    public void addJewelryProperty(AddJewelryResponse addJewelryResponse) {
        Toast.makeText(this, addJewelryResponse.message, Toast.LENGTH_LONG).show();
    }
}
