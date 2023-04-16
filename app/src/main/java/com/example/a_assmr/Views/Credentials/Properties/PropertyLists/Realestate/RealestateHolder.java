package com.example.a_assmr.Views.Credentials.Properties.PropertyLists.Realestate;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a_assmr.R;

public class RealestateHolder extends RecyclerView.ViewHolder {
    ImageView realIMAGE;
    TextView txtType;
    TextView txtOwner;
    TextView txtLocation;
    public RealestateHolder(@NonNull View itemView) {
        super(itemView);
        realIMAGE = itemView.findViewById(R.id.imgpropRealImage);
        txtType = itemView.findViewById(R.id.txtpropRealType);
        txtOwner = itemView.findViewById(R.id.txtpropRealOwner);
        txtLocation = itemView.findViewById(R.id.txtpropRealLocation);
    }
}
