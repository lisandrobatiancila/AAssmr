package com.example.a_assmr.Views.Credentials.Account.My_Property.FeedBacks;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a_assmr.R;

public class FeedBackHolder extends RecyclerView.ViewHolder {
    ImageView imgUser;
    TextView txtUsername, txtDate, txtUserComments;
    public FeedBackHolder(@NonNull View itemView) {
        super(itemView);
        txtUsername = itemView.findViewById(R.id.txtUsername);
        txtDate = itemView.findViewById(R.id.txtDate);
        txtUserComments = itemView.findViewById(R.id.txtUserComments);
        imgUser = itemView.findViewById(R.id.imgUser);
    }
}
