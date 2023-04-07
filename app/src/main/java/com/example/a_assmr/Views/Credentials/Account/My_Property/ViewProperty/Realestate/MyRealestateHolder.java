package com.example.a_assmr.Views.Credentials.Account.My_Property.ViewProperty.Realestate;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a_assmr.R;

public class MyRealestateHolder extends RecyclerView.ViewHolder {
    ImageView realestateImage;
    ImageView threeDots;
    TextView txtStatus, txtOwner, txtPropertyType, txtRealestateType;
    public MyRealestateHolder(@NonNull View itemView) {
        super(itemView);

        realestateImage = itemView.findViewById(R.id.propImage);
        threeDots = itemView.findViewById(R.id.threeDots);
        txtStatus = itemView.findViewById(R.id.txtRStatus);
        txtOwner = itemView.findViewById(R.id.txtROwner);
        txtPropertyType = itemView.findViewById(R.id.txtRPropType);
        txtRealestateType = itemView.findViewById(R.id.txtRType);
    }
}
