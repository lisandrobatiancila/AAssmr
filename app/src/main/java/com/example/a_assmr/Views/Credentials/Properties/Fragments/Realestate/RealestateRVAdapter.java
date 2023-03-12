package com.example.a_assmr.Views.Credentials.Properties.Fragments.Realestate;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a_assmr.Common;
import com.example.a_assmr.R;
import com.example.a_assmr.Views.Credentials.Properties.Fragments.Realestate.RealestateModel.Realestate;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RealestateRVAdapter extends RecyclerView.Adapter<RealestateHolder> {
    Context context;
    LayoutInflater inflater;
    List<Realestate> realestateList;
    Common common;

    public RealestateRVAdapter(Context context, List<Realestate> realestateList) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        this.realestateList = realestateList;
        this.common = new Common();
    }

    @NonNull
    @Override
    public RealestateHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RealestateHolder(inflater.inflate(R.layout.prop_realestate_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RealestateHolder holder, int position) {
        String image = realestateList.get(position).realestateIMG;
        String [] imageArray = image.substring(1, image.length()-1).split(",");

        Picasso.get().load(common.getApiURI()+"/"+imageArray[0].replace("\"", "")).placeholder(R.drawable.ic_launcher_background).into(holder.realIMAGE);
        holder.txtType.setText(realestateList.get(position).realestateType);
        holder.txtOwner.setText("Owner: "+realestateList.get(position).realestateOwner);
        holder.txtLocation.setText("Location: "+realestateList.get(position).realestateLocation);
    }

    @Override
    public int getItemCount() {
        return realestateList.size();
    }
}