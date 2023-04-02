package com.example.a_assmr.Views.Credentials.Account.My_Property.ViewProperty.Jewelry;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a_assmr.R;

public class MyJewelryHolder extends RecyclerView.ViewHolder {
    ImageView propImage;
    TextView txtOwner, txtJewelryName, txtJewelryModel, txtStatus;
    public MyJewelryHolder(@NonNull View itemView) {
        super(itemView);

        propImage = itemView.findViewById(R.id.propImage);
        txtOwner = itemView.findViewById(R.id.txtJOwner);
        txtJewelryName = itemView.findViewById(R.id.txtJName);
        txtJewelryModel = itemView.findViewById(R.id.txtJModel);
        txtStatus = itemView.findViewById(R.id.txtJStatus);
    }
}
