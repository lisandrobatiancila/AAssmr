package com.example.a_assmr.Views.Credentials.Account.My_Property.Inquiries.MyInquiredProperty;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a_assmr.R;
import com.example.a_assmr.Views.Credentials.Account.My_Property.Inquiries.MyInquiredProperty.Model.UserInquiriesModel;

import java.util.List;

public class MyInquiriesAdapter extends RecyclerView.Adapter<MyInquiriesHolder> {
    Context context;
    List<UserInquiriesModel> inquiriesList;
    LayoutInflater inflater;

    public MyInquiriesAdapter(Context context, List<UserInquiriesModel> inquiriesList) {
        this.context = context;
        this.inquiriesList = inquiriesList;
        this.inflater = LayoutInflater.from(context);
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
    }

    @Override
    public int getItemCount() {
        return inquiriesList.size();
    }
}
