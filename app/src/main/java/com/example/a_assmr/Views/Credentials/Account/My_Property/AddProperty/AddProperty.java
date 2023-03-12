package com.example.a_assmr.Views.Credentials.Account.My_Property.AddProperty;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.a_assmr.R;
import com.example.a_assmr.Views.Credentials.Account.My_Property.AddProperty.Realestate.AddRealestateProperty;
import com.example.a_assmr.Views.Credentials.Account.My_Property.AddProperty.Vehicle.AddVehicleProperty;

import java.util.ArrayList;
import java.util.List;

public class AddProperty extends Fragment {
    FrameLayout frameLayout;
    Spinner spinner_chooseProperty; // choose what property type to post
    List<String> propertyType = new ArrayList<String>();
    FragmentTransaction transaction;
    AddVehicleProperty addVehicleProperty;
    AddRealestateProperty addRealestateProperty;
    @SuppressLint("MissingInflatedId")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.account_home_add_property, container, false);
        frameLayout = view.findViewById(R.id.accntAddPropertyFrameLayout);
        spinner_chooseProperty = view.findViewById(R.id.spinnerAccntChooseProperty);
        addVehicleProperty = new AddVehicleProperty();
        addRealestateProperty = new AddRealestateProperty();

        setPropertyType();

        spinner_chooseProperty.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i) {
                    case 1:
                        getChildFragmentManager().beginTransaction().remove(addRealestateProperty).commit();
                        getChildFragmentManager().beginTransaction().add(R.id.accntAddPropertyFrameLayout, addVehicleProperty).commit();
                    break;
                    case 2:
                        getChildFragmentManager().beginTransaction().remove(addVehicleProperty).commit();
                        getChildFragmentManager().beginTransaction().add(R.id.accntAddPropertyFrameLayout, addRealestateProperty).commit();
                    break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        return view;
    }
    private void setPropertyType() {
        propertyType.add("--Choose--");
        propertyType.add("Vehicle");
        propertyType.add("Realestate");
        propertyType.add("Jewelry");

        ArrayAdapter adapter = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_item, propertyType);
        spinner_chooseProperty.setAdapter(adapter);
    }
}
