package com.example.a_assmr.Views.Credentials.Properties.PropertyLists.Realestate;

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
import com.example.a_assmr.Views.Credentials.Properties.PropertyLists.Realestate.RealestateModel.Realestate;
import com.example.a_assmr.Views.Credentials.Properties.View.PropertyViewCertainRealestate;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RealestateRVAdapter extends RecyclerView.Adapter<RealestateHolder> {
    Context context;
    LayoutInflater inflater;
    List<Realestate> realestateList;
    Common common;
    AlertDialog dialog;
    public RealestateRVAdapter(Context context, List<Realestate> realestateList) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        this.realestateList = realestateList;
        this.common = new Common();
    }

    @NonNull
    @Override
    public RealestateHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RealestateHolder(inflater.inflate(R.layout.prop_realestate_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RealestateHolder holder, int position) {
        int pos = position;
        String image = realestateList.get(position).realestateIMG;
        String [] imageArray = image.substring(1, image.length()-1).split(",");

        Picasso.get().load(common.getApiURI()+"/"+imageArray[0].replace("\"", "")).placeholder(R.drawable.ic_launcher_background).into(holder.realIMAGE);
        holder.txtType.setText(realestateList.get(position).realestateType);
        holder.txtOwner.setText("Owner: "+realestateList.get(position).realestateOwner);
        holder.txtLocation.setText("Location: "+realestateList.get(position).realestateLocation);

        holder.btnAssume.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int propertyID = realestateList.get(pos).propertyID;
                ActiveUserSharedPref sharedPref = new ActiveUserSharedPref(context);
                final int userID = sharedPref.activeUserID();
                AssumptionForm assumptionForm = new AssumptionForm(context, userID, propertyID);

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
                Intent i_view_realestate = new Intent(context, PropertyViewCertainRealestate.class);
                i_view_realestate.putExtra("property_id", realestateList.get(pos).propertyID);

                context.startActivity(i_view_realestate);
            }
        });
    }

    @Override
    public int getItemCount() {
        return realestateList.size();
    }
}