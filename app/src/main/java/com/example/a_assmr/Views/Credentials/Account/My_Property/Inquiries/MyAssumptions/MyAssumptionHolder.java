package com.example.a_assmr.Views.Credentials.Account.My_Property.Inquiries.MyAssumptions;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a_assmr.R;

public class MyAssumptionHolder extends RecyclerView.ViewHolder {
    ImageView propertyIMG, threeDotsIMG;
    TextView txtInfo1, txtInfo2, txtInfo3, txtInfo4, txtInfo5;
    //txtInfo1 is 'owner'; txtInfo2 is 'generic' and txtInfo3; txtInfo4 is 'image'; txtInfo5 is 'property type'
    public MyAssumptionHolder(@NonNull View itemView) {
        super(itemView);
        threeDotsIMG = itemView.findViewById(R.id.threeDots);
        propertyIMG = itemView.findViewById(R.id.propertyImage);
        txtInfo1 = itemView.findViewById(R.id.txtInfo1); txtInfo2 = itemView.findViewById(R.id.txtInfo2);
        txtInfo3 = itemView.findViewById(R.id.txtInfo3); txtInfo5 = itemView.findViewById(R.id.txtInfo4);
    }
}
