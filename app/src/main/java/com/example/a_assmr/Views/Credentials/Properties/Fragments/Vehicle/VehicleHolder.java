package com.example.a_assmr.Views.Credentials.Properties.Fragments.Vehicle;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a_assmr.R;

public class VehicleHolder extends RecyclerView.ViewHolder {
    TextView txtVehicleOwner, txtVehicleBrand, txtVehicleModel, txtVehicleLocation;
    ImageView propVehImage;
    public VehicleHolder(@NonNull View itemView) {
        super(itemView);
        txtVehicleOwner = itemView.findViewById(R.id.txtpropVehOwner);
        txtVehicleBrand = itemView.findViewById(R.id.txtpropVehBrand);
        txtVehicleModel = itemView.findViewById(R.id.txtpropVehModel);
        txtVehicleLocation = itemView.findViewById(R.id.txtpropVehLocation);
        propVehImage = itemView.findViewById(R.id.propVehImage);
    }
}
