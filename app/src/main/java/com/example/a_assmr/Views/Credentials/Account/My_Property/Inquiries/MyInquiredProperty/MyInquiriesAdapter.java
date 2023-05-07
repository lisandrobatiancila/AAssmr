package com.example.a_assmr.Views.Credentials.Account.My_Property.Inquiries.MyInquiredProperty;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a_assmr.Common;
import com.example.a_assmr.R;
import com.example.a_assmr.Views.Credentials.Account.My_Property.Inquiries.MyInquiredProperty.Model.UserInquiriesModel;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MyInquiriesAdapter extends RecyclerView.Adapter<MyInquiriesHolder> {
    Context context;
    List<UserInquiriesModel> inquiriesList;
    LayoutInflater inflater;
    Common common;

    public MyInquiriesAdapter(Context context, List<UserInquiriesModel> inquiriesList) {
        this.context = context;
        this.inquiriesList = inquiriesList;
        this.inflater = LayoutInflater.from(context);
        this.common = new Common();
    }

    @NonNull
    @Override
    public MyInquiriesHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyInquiriesHolder(inflater.inflate(R.layout.accnt_inqrs_my_inquiries_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyInquiriesHolder holder, int position) {
        holder.txtAssumerName.setText("Assumer: "+inquiriesList.get(position).getUserFname());
        holder.txtAssumerContactno.setText("Contactno: "+inquiriesList.get(position).getUserContactno());
        holder.txtStatus.setText("Status: "+inquiriesList.get(position).getPropertyStatus());

        String[] image = inquiriesList.get(position).getImage().substring(1, inquiriesList.get(position).getImage().length()-1).split(",");
        Picasso.get().load(common.getApiURI()+"/"+image[0].replaceAll("\"","")).placeholder(R.drawable.ic_launcher_background).into(holder.imageView);
        PopupMenu popupMenu = new PopupMenu(context, holder.imageThreeDots);
        popupMenu.getMenuInflater().inflate(R.menu.my_inquiries_menu, popupMenu.getMenu());

        holder.imageThreeDots.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupMenu.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return inquiriesList.size();
    }
}