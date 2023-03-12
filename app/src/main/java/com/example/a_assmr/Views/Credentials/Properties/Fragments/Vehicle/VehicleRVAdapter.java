package com.example.a_assmr.Views.Credentials.Properties.Fragments.Vehicle;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a_assmr.Common;
import com.example.a_assmr.R;
import com.example.a_assmr.Views.Credentials.Properties.Fragments.Vehicle.VehicleModel.Vehicles;
import com.squareup.picasso.Picasso;

import java.util.List;

public class VehicleRVAdapter extends RecyclerView.Adapter <VehicleHolder> {
    Context context;
    LayoutInflater inflater;
    List<Vehicles> vehiclesList;
    Common common;
    public VehicleRVAdapter(Context context, List<Vehicles> vehiclesList) {
        this.context = context;
        this.vehiclesList = vehiclesList;
        this.inflater = LayoutInflater.from(context);
        this.common = new Common();
    }

    @NonNull
    @Override
    public VehicleHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new VehicleHolder(inflater.inflate(R.layout.prop_vehicle_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull VehicleHolder holder, int position) {
        holder.txtVehicleOwner.setText("Owner: "+vehiclesList.get(position).vehicleOwner);
        holder.txtVehicleBrand.setText("Brand: "+vehiclesList.get(position).vehicleBrand);
        holder.txtVehicleModel.setText("Model: "+vehiclesList.get(position).vehicleModel);
        holder.txtVehicleLocation.setText("Location: "+vehiclesList.get(position).vehicleLocation);
        String image = vehiclesList.get(position).vehicleIMAGES;
        String[] imageArray = image.substring(1, image.length()-1).split(",");

        Picasso.get().load(common.getApiURI()+"/"+imageArray[0].replaceAll("\"","")).placeholder(R.drawable.ic_launcher_background).into(holder.propVehImage);
    }

    @Override
    public int getItemCount() {
        return vehiclesList.size();
    }
}
