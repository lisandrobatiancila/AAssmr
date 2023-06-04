package com.example.a_assmr.Views.Credentials.Account.My_Property.Inquiries.MyInquiredProperty;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a_assmr.R;

public class MyInquiriesHolder extends RecyclerView.ViewHolder {
    ImageView imageView, imageThreeDots;
    TextView txtStatus, txtInfo1, txtInfo2, txtAssmptnCount;
    public MyInquiriesHolder(@NonNull View itemView) {
        super(itemView);

        imageView = itemView.findViewById(R.id.propertyImage);
        imageThreeDots = itemView.findViewById(R.id.threeDots);

        txtStatus = itemView.findViewById(R.id.txtStatus);
        txtInfo1 = itemView.findViewById(R.id.txtAssumerName);
        txtInfo2 = itemView.findViewById(R.id.txtAssumerContacto);
        txtAssmptnCount = itemView.findViewById(R.id.txtAssmptnCount);
    }
}
