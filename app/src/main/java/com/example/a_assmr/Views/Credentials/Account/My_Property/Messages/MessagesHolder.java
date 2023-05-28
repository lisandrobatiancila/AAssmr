package com.example.a_assmr.Views.Credentials.Account.My_Property.Messages;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a_assmr.R;

public class MessagesHolder extends RecyclerView.ViewHolder {
    CardView cardView;
    TextView txtUserFullname, txtDate, txtMessage;
    ImageView imgProfile;
    public MessagesHolder(@NonNull View itemView) {
        super(itemView);
        cardView = itemView.findViewById(R.id.messageCardView);

        imgProfile = itemView.findViewById(R.id.chattProfile);

        txtUserFullname = itemView.findViewById(R.id.chattUserFullname);
        txtDate = itemView.findViewById(R.id.chattDate);
        txtMessage = itemView.findViewById(R.id.chattMessage);
    }
}
