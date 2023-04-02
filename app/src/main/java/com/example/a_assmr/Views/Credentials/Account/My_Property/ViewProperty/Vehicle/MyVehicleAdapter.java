package com.example.a_assmr.Views.Credentials.Account.My_Property.ViewProperty.Vehicle;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a_assmr.Common;
import com.example.a_assmr.R;
import com.example.a_assmr.Views.Credentials.Account.My_Property.ViewProperty.Vehicle.Model.MyVehicleLists;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MyVehicleAdapter extends RecyclerView.Adapter<MyVehicleHolder> {
    Context context;
    LayoutInflater inflater;
    List<MyVehicleLists> vehicleLists;
    Common common;
    public MyVehicleAdapter(Context context, List<MyVehicleLists> vehicleLists) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        this.vehicleLists = vehicleLists;
        this.common = new Common();
    }

    @NonNull
    @Override
    public MyVehicleHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyVehicleHolder(inflater.inflate(R.layout.account_my_vehicle_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyVehicleHolder holder, int position) {
        holder.txtOwner.setText("Owner: "+vehicleLists.get(position).getVehicleOwner());
        holder.txtVehicleName.setText("Vehicle name: "+vehicleLists.get(position).getVehicleBrand());
        holder.txtVehicleModel.setText("Vehicle model: "+vehicleLists.get(position).getVehicleModel());
        holder.txtStatus.setText("Status: "+vehicleLists.get(position).getPropertyStatus());
        String image = vehicleLists.get(position).vehicleIMAGES;
        String[] imageArray = image.substring(1, image.length()-1).split(",");

        Picasso.get().load(common.getApiURI()+"/"+imageArray[0].replaceAll("\"", "")).placeholder(R.drawable.ic_launcher_background).into(holder.imgPropImage);
    }

    @Override
    public int getItemCount() {
        return vehicleLists.size();
    }
}
