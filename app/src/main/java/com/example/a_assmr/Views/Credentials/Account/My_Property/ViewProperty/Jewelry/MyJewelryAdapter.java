package com.example.a_assmr.Views.Credentials.Account.My_Property.ViewProperty.Jewelry;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a_assmr.Common;
import com.example.a_assmr.R;
import com.example.a_assmr.Views.Credentials.Account.My_Property.ViewProperty.Jewelry.Model.MyJewelryLists;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MyJewelryAdapter extends RecyclerView.Adapter<MyJewelryHolder> {
    Context context;
    LayoutInflater inflater;
    List<MyJewelryLists> jewelryLists;
    Common common;

    public MyJewelryAdapter(Context context, List<MyJewelryLists> jewelryLists) {
        this.context = context;
        this.jewelryLists = jewelryLists;
        this.inflater = LayoutInflater.from(context);
        this.common = new Common();
    }

    @NonNull
    @Override
    public MyJewelryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyJewelryHolder(inflater.inflate(R.layout.account_my_jewelry_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyJewelryHolder holder, int position) {
        holder.txtOwner.setText("Owner: "+jewelryLists.get(position).getJewelryOwner());
        holder.txtJewelryName.setText("Jewelry name: "+jewelryLists.get(position).getJewelryName());
        holder.txtJewelryModel.setText("Jewelry model: "+jewelryLists.get(position).getJewelryModel());
        holder.txtStatus.setText("Status: "+jewelryLists.get(position).getPropertyStatus());

        String image = jewelryLists.get(position).getJewelryIMG();
        String[] imageArray = image.substring(1, image.length()-1).split(",");

        Picasso.get().load(common.getApiURI()+"/"+imageArray[0].replaceAll("\"", "")).placeholder(R.drawable.ic_launcher_background).into(holder.propImage);
    }

    @Override
    public int getItemCount() {
        return jewelryLists.size();
    }
}
