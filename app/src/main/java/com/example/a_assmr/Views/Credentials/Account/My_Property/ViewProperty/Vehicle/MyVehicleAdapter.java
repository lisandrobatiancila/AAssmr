package com.example.a_assmr.Views.Credentials.Account.My_Property.ViewProperty.Vehicle;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a_assmr.Common;
import com.example.a_assmr.CommonDir.GenericClass;
import com.example.a_assmr.R;
import com.example.a_assmr.Views.Credentials.Account.My_Property.ViewProperty.ItemViewModel;
import com.example.a_assmr.Views.Credentials.Account.My_Property.ViewProperty.VURCertainProperty.Controller.RemoveCertainPropController;
import com.example.a_assmr.Views.Credentials.Account.My_Property.ViewProperty.VURCertainProperty.Model.RemoveServerResponse;
import com.example.a_assmr.Views.Credentials.Account.My_Property.ViewProperty.VURCertainProperty.UpdateCertainVehicle;
import com.example.a_assmr.Views.Credentials.Account.My_Property.ViewProperty.VURCertainProperty.ViewCertainVehicle;
import com.example.a_assmr.Views.Credentials.Account.My_Property.ViewProperty.Vehicle.Model.MyVehicleLists;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MyVehicleAdapter extends RecyclerView.Adapter<MyVehicleHolder> {
    Context context;
    LayoutInflater inflater;
    List<MyVehicleLists> vehicleLists;
    Common common;
    ItemViewModel itemViewModel;
    AlertDialog dialog;
    public MyVehicleAdapter(Context context, List<MyVehicleLists> vehicleLists) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        this.vehicleLists = vehicleLists;
        this.common = new Common();
        this.itemViewModel = new ViewModelProvider((ViewModelStoreOwner) context).get(ItemViewModel.class);
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
        int pos = position;

        Picasso.get().load(common.getApiURI()+"/"+imageArray[0].replaceAll("\"", "")).placeholder(R.drawable.ic_launcher_background).into(holder.imgPropImage);

        PopupMenu popupMenu = new PopupMenu(context, holder.imgThreeDots);
        popupMenu.getMenuInflater().inflate(R.menu.popup_menu, popupMenu.getMenu());

        holder.imgThreeDots.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupMenu.show();
            }
        });
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @SuppressLint("MissingInflatedId")
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                int menu_pos = menuItem.getItemId();
                switch (menu_pos) {
                    case R.id.viewProperty:
                        Intent i_certain_vehicle = new Intent(context, ViewCertainVehicle.class);
                        i_certain_vehicle.putExtra("vehicleID", vehicleLists.get(pos).vehicleID);
                        i_certain_vehicle.putExtra("owner", vehicleLists.get(pos).getVehicleOwner());
                        i_certain_vehicle.putExtra("contactno", vehicleLists.get(pos).getVehicleContactno());
                        i_certain_vehicle.putExtra("brand", vehicleLists.get(pos).getVehicleBrand());
                        i_certain_vehicle.putExtra("model", vehicleLists.get(pos).getVehicleModel());
                        i_certain_vehicle.putExtra("location", vehicleLists.get(pos).getVehicleLocation());
                        i_certain_vehicle.putExtra("downpayment", vehicleLists.get(pos).getVehicleDownpayment());
                        i_certain_vehicle.putExtra("installmentpaid", vehicleLists.get(pos).getVehicleInstallmentPaid());
                        i_certain_vehicle.putExtra("installmentduration", vehicleLists.get(pos).getVehicleInstallmentDuration());
                        i_certain_vehicle.putExtra("delinquent", vehicleLists.get(pos).getVehicleDelinquent());
                        i_certain_vehicle.putExtra("description", vehicleLists.get(pos).getDescription());
                        i_certain_vehicle.putExtra("assumptionCount", vehicleLists.get(pos).getAssumptionCount());
                        i_certain_vehicle.putExtra("propertyStatus", vehicleLists.get(pos).getPropertyStatus());
                        i_certain_vehicle.putExtra("image", vehicleLists.get(pos).getVehicleIMAGES());
                        context.startActivity(i_certain_vehicle);
                    break;
                    case R.id.updateProperty:
                        Intent u_certain_vehicle = new Intent(context, UpdateCertainVehicle.class);
                        u_certain_vehicle.putExtra("vehicleID", vehicleLists.get(pos).vehicleID);
                        u_certain_vehicle.putExtra("propertyID", vehicleLists.get(pos).getPropertyID());
                        u_certain_vehicle.putExtra("owner", vehicleLists.get(pos).getVehicleOwner());
                        u_certain_vehicle.putExtra("contactno", vehicleLists.get(pos).getVehicleContactno());
                        u_certain_vehicle.putExtra("brand", vehicleLists.get(pos).getVehicleBrand());
                        u_certain_vehicle.putExtra("model", vehicleLists.get(pos).getVehicleModel());
                        u_certain_vehicle.putExtra("location", vehicleLists.get(pos).getVehicleLocation());
                        u_certain_vehicle.putExtra("downpayment", vehicleLists.get(pos).getVehicleDownpayment());
                        u_certain_vehicle.putExtra("installmentpaid", vehicleLists.get(pos).getVehicleInstallmentPaid());
                        u_certain_vehicle.putExtra("installmentduration", vehicleLists.get(pos).getVehicleInstallmentDuration());
                        u_certain_vehicle.putExtra("delinquent", vehicleLists.get(pos).getVehicleDelinquent());
                        u_certain_vehicle.putExtra("description", vehicleLists.get(pos).getDescription());
                        u_certain_vehicle.putExtra("assumptionCount", vehicleLists.get(pos).getAssumptionCount());
                        u_certain_vehicle.putExtra("propertyStatus", vehicleLists.get(pos).getPropertyStatus());
                        u_certain_vehicle.putExtra("image", vehicleLists.get(pos).getVehicleIMAGES());
                        context.startActivity(u_certain_vehicle);
                    break;
                    case R.id.removeProperty:
                        int propertyID = vehicleLists.get(pos).getPropertyID();
                        String brand = vehicleLists.get(pos).getVehicleBrand();
                        String model = vehicleLists.get(pos).getVehicleModel();

                        AlertDialog.Builder builder = new AlertDialog.Builder(context);
                        View view = inflater.inflate(R.layout.prompt_property_remove, null);
                        builder.setView(view);
                        builder.setTitle("Message");

                        LinearLayout layoutConfirmation = view.findViewById(R.id.layoutConfirmation);
                        LinearLayout layoutRemoving = view.findViewById(R.id.layoutRemoving);
                        LinearLayout layoutActions = view.findViewById(R.id.layoutActions);

                        TextView txtPropInfo1, txtPropInfo2;
                        txtPropInfo1 = view.findViewById(R.id.txtPropInfo1);
                        txtPropInfo2 = view.findViewById(R.id.txtPropInfo2);

                        txtPropInfo1.setText("Brand: "+brand);
                        txtPropInfo2.setText("Model: "+model);

                        Button btnYes = view.findViewById(R.id.btnYes);
                        Button btnNo = view.findViewById(R.id.btnNo);

                        btnYes.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                layoutConfirmation.setVisibility(View.GONE);
                                layoutActions.setVisibility(View.GONE);
                                layoutRemoving.setVisibility(View.VISIBLE);

                                RemoveCertainPropController rcpc = new RemoveCertainPropController(context, dialog);
                                GenericClass<MyVehicle> genericClass = new GenericClass<>();
                                genericClass.setCertainClass(new MyVehicle());
                                rcpc.removeCertainProperty(propertyID,"vehicle", genericClass);

                                itemViewModel.getSelectedItem().observe((LifecycleOwner) context, item -> {
                                    Object obj = item.getCertainGenericClass();
                                    if(obj instanceof RemoveServerResponse) {
                                        if(((RemoveServerResponse) obj).code == 200) {
                                            Toast.makeText(context, "Successfully removed.", Toast.LENGTH_SHORT).show();
                                            vehicleLists.remove(pos);
                                        }
                                        else
                                            Toast.makeText(context, "Removing faield.", Toast.LENGTH_LONG).show();
                                    }
                                });
                            }
                        });
                        btnNo.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                              dialog.dismiss();
                            }
                        });
                        dialog = builder.create();
                        dialog.setCancelable(false);
                        dialog.show();
                    break;
                }
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return vehicleLists.size();
    }
}
