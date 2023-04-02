package com.example.a_assmr.Views.Credentials.Account.My_Property.ViewProperty.Vehicle;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a_assmr.R;

public class MyVehicleHolder extends RecyclerView.ViewHolder {
    TextView txtOwner, txtVehicleName, txtVehicleModel, txtStatus;
    ImageView imgPropImage, imgThreeDots;
    public MyVehicleHolder(@NonNull View itemView) {
        super(itemView);

        txtOwner = itemView.findViewById(R.id.txtVOwner);
        txtVehicleName = itemView.findViewById(R.id.txtVName);
        txtVehicleModel = itemView.findViewById(R.id.txtVModel);
        txtStatus = itemView.findViewById(R.id.txtVStatus);

        imgPropImage = itemView.findViewById(R.id.propImage);
        imgThreeDots = itemView.findViewById(R.id.threeDots);
    }
}
