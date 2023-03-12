package com.example.a_assmr.Views.Credentials.Properties.Fragments.Jewelry;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a_assmr.Common;
import com.example.a_assmr.R;
import com.example.a_assmr.Views.Credentials.Properties.Fragments.Jewelry.JewelryModel.Jewelries;
import com.squareup.picasso.Picasso;

import java.util.List;

public class JewelryRVAdapter extends RecyclerView.Adapter<JewelryHolder> {
    Context context;
    LayoutInflater inflater;
    List<Jewelries> jewelriesList;
    Common common;

    public JewelryRVAdapter(Context context, List<Jewelries> jewelriesList) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        this.jewelriesList = jewelriesList;
        this.common = new Common();
    }

    @NonNull
    @Override
    public JewelryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new JewelryHolder(inflater.inflate(R.layout.prop_jewelry_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull JewelryHolder holder, int position) {
        String image = jewelriesList.get(position).jewelryIMG;
        String [] imageArray = image.substring(1, image.length()-1).split(",");
        Picasso.get().load(common.getApiURI()+"/"+imageArray[0].replaceAll("\"", "")).placeholder(R.drawable.ic_launcher_background).into(holder.jewelryIMG);

        holder.txtType.setText(jewelriesList.get(position).jewelryModel);
        holder.txtModel.setText("Jewelry name: "+jewelriesList.get(position).jewelryName);
        holder.txtOwner.setText("Owner: "+jewelriesList.get(position).jewelryOwner);
        holder.txtLocation.setText("Location: "+jewelriesList.get(position).jewelryLocation);
    }

    @Override
    public int getItemCount() {
        return jewelriesList.size();
    }
}
