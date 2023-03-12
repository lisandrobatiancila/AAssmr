package com.example.a_assmr.Views.Credentials.Account;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.a_assmr.R;
import com.example.a_assmr.Views.Credentials.Account.My_Property.AddProperty.AddProperty;
import com.example.a_assmr.Views.Credentials.Account.My_Property.ViewProperty.ViewProperty;
import com.example.a_assmr.Views.Credentials.Properties.Properties;
import com.google.android.material.navigation.NavigationView;

public class AccountHome extends AppCompatActivity {
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
                    break;
                    case R.id.accntMessages:
                    break;
                    case R.id.accntInquiries:
                    break;
                    case R.id.accntSettings:
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
}
