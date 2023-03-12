package com.example.a_assmr.Views.Credentials.Properties;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.example.a_assmr.R;
import com.example.a_assmr.Views.Credentials.Account.AccountHome;
import com.google.android.material.tabs.TabLayout;

public class Properties extends AppCompatActivity {
    TabLayout tabLayout;
    FragmentPageAdapter fpa;
    ViewPager2 pager2;
    ImageButton goToHome;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vcs_properties);

        goToHome = findViewById(R.id.toolbarHome);
        tabLayout = findViewById(R.id.propTabLayout);
        fpa = new FragmentPageAdapter(this);
        pager2 = findViewById(R.id.propviewPager2);

        pager2.setAdapter(fpa);

        goToHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i_goHome = new Intent(getApplicationContext(), AccountHome.class);
                startActivity(i_goHome);
            }
        });
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                pager2.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        pager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                tabLayout.getTabAt(position).select();
            }
        });
    }
}
