package com.example.a_assmr.Views.Credentials.Account.My_Property.Inquiries.MyAssumptions;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a_assmr.Common;
import com.example.a_assmr.R;
import com.example.a_assmr.Views.Credentials.Account.My_Property.Inquiries.MyAssumptions.Model.InquiriesAssumptionModel;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MyAssumptionAdapter extends RecyclerView.Adapter<MyAssumptionHolder> {
    Context context;
    LayoutInflater inflater;
    List<InquiriesAssumptionModel> myAssumptionLists;
    Common common;

    public MyAssumptionAdapter(Context context, List<InquiriesAssumptionModel> myAssumptionLists) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        this.myAssumptionLists = myAssumptionLists;
        this.common = new Common();
    }

    @NonNull
    @Override
    public MyAssumptionHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyAssumptionHolder(inflater.inflate(R.layout.accnt_inqrs_my_assumptions_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyAssumptionHolder holder, int position) {
        switch (myAssumptionLists.get(position).getInfo5()) {
            case "vehicle":
                holder.txtInfo2.setText("Brand: "+myAssumptionLists.get(position).getInfo2());
                holder.txtInfo3.setText("Model: "+myAssumptionLists.get(position).getInfo3());
                holder.txtStatus.setText("Status: "+myAssumptionLists.get(position).getInfo6());
            break;
            case "jewelry":
                holder.txtInfo2.setText("Jewelry Name: "+myAssumptionLists.get(position).getInfo2());
                holder.txtInfo3.setText("Jewelry Model: "+myAssumptionLists.get(position).getInfo3());
                holder.txtStatus.setText("Status: "+myAssumptionLists.get(position).getInfo6());
                break;
            case "realestate":
                holder.txtInfo2.setText("Realestate Type: "+myAssumptionLists.get(position).getInfo2());
                holder.txtStatus.setText("Status: "+myAssumptionLists.get(position).getInfo6());
                holder.txtInfo3.setVisibility(View.GONE);
            break;
        }
        holder.txtInfo1.setText("Owner: "+myAssumptionLists.get(position).getInfo1());
        holder.txtInfo5.setText("Property Type: "+myAssumptionLists.get(position).getInfo5());
        String[] image = myAssumptionLists.get(position).getInfo4().substring(1, myAssumptionLists.get(position).getInfo4().length()-1).split(",");

        Picasso.get().load(common.getApiURI()+"/"+image[0].replaceAll("\"","")).placeholder(R.drawable.ic_launcher_background).into(holder.propertyIMG);
        PopupMenu popupMenu = new PopupMenu(context, holder.threeDotsIMG);
        popupMenu.getMenuInflater().inflate(R.menu.my_assumption_menu, popupMenu.getMenu());

        holder.threeDotsIMG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupMenu.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return myAssumptionLists.size();
    }
}
