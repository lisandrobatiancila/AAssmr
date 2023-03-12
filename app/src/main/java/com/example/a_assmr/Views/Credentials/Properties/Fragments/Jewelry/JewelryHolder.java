package com.example.a_assmr.Views.Credentials.Properties.Fragments.Jewelry;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a_assmr.R;

public class JewelryHolder extends RecyclerView.ViewHolder {
    ImageView jewelryIMG;
    TextView txtType, txtOwner, txtModel, txtLocation;
    Button btnAssume, btnView;
    public JewelryHolder(@NonNull View itemView) {
        super(itemView);

        jewelryIMG = itemView.findViewById(R.id.imgpropJewelImage);
        txtType = itemView.findViewById(R.id.txtpropJewelType);
        txtOwner = itemView.findViewById(R.id.txtpropJewelOwner);
        txtModel = itemView.findViewById(R.id.txtpropJewelModel);
        txtLocation = itemView.findViewById(R.id.txtpropJewelLocation);
        btnAssume = itemView.findViewById(R.id.btnpropJewelAssume);
        btnView = itemView.findViewById(R.id.btnpropJewelView);
    }
}
