package com.example.a_assmr.Views.Credentials.Properties.PropertyLists.Vehicle;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a_assmr.Common;
import com.example.a_assmr.CommonDir.ActiveUserSharedPref;
import com.example.a_assmr.R;
import com.example.a_assmr.Views.Credentials.Properties.Assume.AssumptionForm;
import com.example.a_assmr.Views.Credentials.Properties.PropertyLists.Vehicle.VehicleModel.Vehicles;
import com.example.a_assmr.Views.Credentials.Properties.View.PropertyViewCertainVehicle;
import com.squareup.picasso.Picasso;

import java.util.List;

public class VehicleRVAdapter extends RecyclerView.Adapter <VehicleHolder> {
    Context context;
    LayoutInflater inflater;
    List<Vehicles> vehiclesList;
    Common common;
    AlertDialog dialog;
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
        int pos = position;
        holder.txtVehicleOwner.setText("Owner: "+vehiclesList.get(position).vehicleOwner);
        holder.txtVehicleBrand.setText("Brand: "+vehiclesList.get(position).vehicleBrand);
        holder.txtVehicleModel.setText("Model: "+vehiclesList.get(position).vehicleModel);
        holder.txtVehicleLocation.setText("Location: "+vehiclesList.get(position).vehicleLocation);
        String image = vehiclesList.get(position).vehicleIMAGES;
        String[] imageArray = image.substring(1, image.length()-1).split(",");

        Picasso.get().load(common.getApiURI()+"/"+imageArray[0].replaceAll("\"","")).placeholder(R.drawable.ic_launcher_background).into(holder.propVehImage);

        holder.btnAssume.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int propertyID = vehiclesList.get(pos).propertyID;
                ActiveUserSharedPref sharedPref = new ActiveUserSharedPref(context);

                AssumptionForm assumptionForm = new AssumptionForm(context, sharedPref.activeUserID(),
                        propertyID);
                AlertDialog.Builder builder = new AlertDialog.Builder(context);

                View assumption_form_modal = assumptionForm.AssumptionInitForm(context);
                builder.setView(assumption_form_modal);

                builder.setCancelable(false);

                dialog = builder.create();
                assumptionForm.setDialogInstance(dialog);
                assumptionForm.getAssumerInformation(); // userID and propertyID was set already

                dialog.show();
            }
        }); // assume a vehicle
        holder.btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ivcv = new Intent(context, PropertyViewCertainVehicle.class); // i_view_certain_vehicle
                ivcv.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                ivcv.putExtra("vehicle_id", vehiclesList.get(pos).vehicleID); // not used.
                ivcv.putExtra("property_id", vehiclesList.get(pos).propertyID);
                context.startActivity(ivcv);
            }
        }); // view a certain vehicle
    }

    @Override
    public int getItemCount() {
        return vehiclesList.size();
    }
}
