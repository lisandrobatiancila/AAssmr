package com.example.a_assmr.Views.Credentials.Properties.PropertyLists.Jewelry;

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
import com.example.a_assmr.Views.Credentials.Properties.PropertyLists.Jewelry.JewelryModel.Jewelries;
import com.example.a_assmr.Views.Credentials.Properties.View.PropertyViewCertainJewelry;
import com.squareup.picasso.Picasso;

import java.util.List;

public class JewelryRVAdapter extends RecyclerView.Adapter<JewelryHolder> {
    Context context;
    LayoutInflater inflater;
    List<Jewelries> jewelriesList;
    Common common;
    AlertDialog dialog;
    public JewelryRVAdapter(Context context, List<Jewelries> jewelriesList) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        this.jewelriesList = jewelriesList;
        this.common = new Common();
    }

    @NonNull
    @Override
    public JewelryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new JewelryHolder(inflater.inflate(R.layout.prop_jewelry_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull JewelryHolder holder, int position) {
        int pos = position;
        String image = jewelriesList.get(position).jewelryIMG;
        String [] imageArray = image.substring(1, image.length()-1).split(",");
        Picasso.get().load(common.getApiURI()+"/"+imageArray[0].replaceAll("\"", "")).placeholder(R.drawable.ic_launcher_background).into(holder.jewelryIMG);

        holder.txtType.setText(jewelriesList.get(position).jewelryModel);
        holder.txtModel.setText("Jewelry name: "+jewelriesList.get(position).jewelryName);
        holder.txtOwner.setText("Owner: "+jewelriesList.get(position).jewelryOwner);
        holder.txtLocation.setText("Location: "+jewelriesList.get(position).jewelryLocation);

        holder.btnAssume.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int propertyID = jewelriesList.get(pos).propertyID;
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
        });

        holder.btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i_view_jewelry = new Intent(context, PropertyViewCertainJewelry.class);
                i_view_jewelry.putExtra("property_id", jewelriesList.get(pos).propertyID);
                context.startActivity(i_view_jewelry);
            }
        });
    }

    @Override
    public int getItemCount() {
        return jewelriesList.size();
    }
}
