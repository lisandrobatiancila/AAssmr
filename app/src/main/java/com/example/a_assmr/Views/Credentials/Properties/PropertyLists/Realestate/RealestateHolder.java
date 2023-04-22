package com.example.a_assmr.Views.Credentials.Properties.PropertyLists.Realestate;

import android.view.View;
import android.widget.Button;
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
    Button btnAssume, btnView;
    public RealestateHolder(@NonNull View itemView) {
        super(itemView);
        realIMAGE = itemView.findViewById(R.id.imgpropRealImage);
        txtType = itemView.findViewById(R.id.txtpropRealType);
        txtOwner = itemView.findViewById(R.id.txtpropRealOwner);
        txtLocation = itemView.findViewById(R.id.txtpropRealLocation);

        btnAssume = itemView.findViewById(R.id.btnpropRealAssume);
        btnView = itemView.findViewById(R.id.btnpropRealView);
    }
}
