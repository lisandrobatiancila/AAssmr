package com.example.a_assmr.Views.Credentials.Account.My_Property.ViewProperty;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.ColorUtils;
import androidx.fragment.app.Fragment;

import com.example.a_assmr.R;
import com.example.a_assmr.Views.Credentials.Account.My_Property.ViewProperty.Jewelry.MyJewelry;
import com.example.a_assmr.Views.Credentials.Account.My_Property.ViewProperty.Vehicle.MyVehicle;

public class ViewProperty extends Fragment {
    CardView vehicleCard, realestateCard, jewelryCard;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.account_home_view_property, container, false);
        initializeCategory(view);

        getChildFragmentManager().beginTransaction().add(R.id.frameLayoutMyProperty, new MyVehicle()).commit();

        return view;
    }
    private void initializeCategory(View view) {
        vehicleCard = view.findViewById(R.id.vehicleCard);
        realestateCard = view.findViewById(R.id.realestateCard);
        jewelryCard = view.findViewById(R.id.jewelryCard);
        TextView txtVehicle, txtRealestate, txtJewelry;
        txtVehicle = view.findViewById(R.id.textVehicle);
        txtRealestate = view.findViewById(R.id.textRealestate);
        txtJewelry = view.findViewById(R.id.textJewelry);
        vehicleCard.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View view) {
                vehicleCard.setCardBackgroundColor(ColorUtils.setAlphaComponent(ContextCompat.getColor(getContext(), R.color.teal_700),255));
                txtVehicle.setTextColor(ColorUtils.setAlphaComponent(ContextCompat.getColor(getContext(), R.color.white),255));

                realestateCard.setCardBackgroundColor(ColorUtils.setAlphaComponent(ContextCompat.getColor(getContext(), R.color.white),255));
                txtRealestate.setTextColor(ColorUtils.setAlphaComponent(ContextCompat.getColor(getContext(), R.color.black),255));

                jewelryCard.setCardBackgroundColor(ColorUtils.setAlphaComponent(ContextCompat.getColor(getContext(), R.color.white), 255));
                txtJewelry.setTextColor(ColorUtils.setAlphaComponent(ContextCompat.getColor(getContext(), R.color.black),255));

                getChildFragmentManager().beginTransaction().replace(R.id.frameLayoutMyProperty, new MyVehicle()).commit();
            }
        });
        realestateCard.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View view) {
                realestateCard.setCardBackgroundColor(ColorUtils.setAlphaComponent(ContextCompat.getColor(getContext(), R.color.teal_700), 255));
                txtRealestate.setTextColor(ColorUtils.setAlphaComponent(ContextCompat.getColor(getContext(), R.color.white), 255));

                vehicleCard.setCardBackgroundColor(ColorUtils.setAlphaComponent(ContextCompat.getColor(getContext(), R.color.white), 255));
                txtVehicle.setTextColor(ColorUtils.setAlphaComponent(ContextCompat.getColor(getContext(), R.color.black),255));

                jewelryCard.setCardBackgroundColor(ColorUtils.setAlphaComponent(ContextCompat.getColor(getContext(), R.color.white), 255));
                txtJewelry.setTextColor(ColorUtils.setAlphaComponent(ContextCompat.getColor(getContext(), R.color.black),255));
            }
        });
        jewelryCard.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View view) {
                jewelryCard.setCardBackgroundColor(ColorUtils.setAlphaComponent(ContextCompat.getColor(getContext(), R.color.teal_700),255));
                txtJewelry.setTextColor(ColorUtils.setAlphaComponent(ContextCompat.getColor(getContext(), R.color.white), 255));

                vehicleCard.setCardBackgroundColor(ColorUtils.setAlphaComponent(ContextCompat.getColor(getContext(), R.color.white), 255));
                txtVehicle.setTextColor(ColorUtils.setAlphaComponent(ContextCompat.getColor(getContext(), R.color.black), 255));

                realestateCard.setCardBackgroundColor(ColorUtils.setAlphaComponent(ContextCompat.getColor(getContext(), R.color.white), 255));
                txtRealestate.setTextColor(ColorUtils.setAlphaComponent(ContextCompat.getColor(getContext(), R.color.black), 255));

                getChildFragmentManager().beginTransaction().replace(R.id.frameLayoutMyProperty, new MyJewelry()).commit();
            }
        });
    }

}
