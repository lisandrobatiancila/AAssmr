package com.example.a_assmr.Views.Credentials.Account.My_Property.ViewProperty.Realestate;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a_assmr.Common;
import com.example.a_assmr.R;
import com.example.a_assmr.Views.Credentials.Account.My_Property.ViewProperty.Realestate.Model.MyRealestateLists;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MyRealestateAdapter extends RecyclerView.Adapter<MyRealestateHolder> {
    Context context;
    LayoutInflater inflater;
    ArrayList<MyRealestateLists> realestatesLists;
    Common common;

    public MyRealestateAdapter(Context context, ArrayList<MyRealestateLists> realestatesLists) {
        this.context = context;
        this.realestatesLists = realestatesLists;
        this.inflater = LayoutInflater.from(context);
        this.common = new Common();
    }

    @NonNull
    @Override
    public MyRealestateHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyRealestateHolder(inflater.inflate(R.layout.account_my_realestate_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyRealestateHolder holder, int position) {
        holder.txtOwner.setText("Owner: "+realestatesLists.get(position).getRealestateOwner());
        holder.txtPropertyType.setText("Property type: Realestate");
        holder.txtRealestateType.setText("Realestate: "+realestatesLists.get(position).getRealestateType());
        holder.txtStatus.setText("Status: "+realestatesLists.get(position).getPropertyStatus());

        String image = realestatesLists.get(position).getRealestateIMG();
        String[] imageArray = image.substring(1, image.length()-1).split((","));
        Picasso.get().load(common.getApiURI()+"/"+imageArray[0].replaceAll("\"", "")).placeholder(R.drawable.ic_launcher_background).into(holder.realestateImage);
    }

    @Override
    public int getItemCount() {
        return realestatesLists.size();
    }
}
