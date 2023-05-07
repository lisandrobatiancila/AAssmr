package com.example.a_assmr.Views.Credentials.Account.My_Property.Inquiries.MyInquiredProperty;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a_assmr.R;

public class MyInquiriesHolder extends RecyclerView.ViewHolder {
    ImageView imageView, imageThreeDots;
    TextView txtStatus, txtAssumerName, txtAssumerContactno;
    public MyInquiriesHolder(@NonNull View itemView) {
        super(itemView);

        imageView = itemView.findViewById(R.id.propertyImage);
        imageThreeDots = itemView.findViewById(R.id.threeDots);

        txtStatus = itemView.findViewById(R.id.txtStatus);
        txtAssumerName = itemView.findViewById(R.id.txtAssumerName);
        txtAssumerContactno = itemView.findViewById(R.id.txtAssumerContacto);
    }
}
