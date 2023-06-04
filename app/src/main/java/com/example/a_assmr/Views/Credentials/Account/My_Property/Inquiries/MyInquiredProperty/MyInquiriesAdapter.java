package com.example.a_assmr.Views.Credentials.Account.My_Property.Inquiries.MyInquiredProperty;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a_assmr.Common;
import com.example.a_assmr.R;
import com.example.a_assmr.Views.Credentials.Account.My_Property.Inquiries.MyAssumptions.PropertyDetails.PropertyDetails;
import com.example.a_assmr.Views.Credentials.Account.My_Property.Inquiries.MyInquiredProperty.AssumerInformation.AssumerInformation;
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
        final int pos = position;
        String type = inquiriesList.get(pos).getType();
        holder.txtAssmptnCount.setText("+ "+inquiriesList.get(pos).getAssumptionCount());
        if(type.equals("vehicle")) {
            holder.txtInfo1.setText("Vehicle Name: "+inquiriesList.get(position).getInfo1());
            holder.txtInfo2.setText("Vehicle Brand: "+inquiriesList.get(position).getInfo2());
            holder.txtStatus.setText("Status: "+inquiriesList.get(position).getPropertyStatus());
        }
        else if(type.equals("jewelries")) {
            holder.txtInfo1.setText("Jewelry Name: "+inquiriesList.get(position).getInfo1());
            holder.txtInfo2.setText("Jewelry Model: "+inquiriesList.get(position).getInfo2());
            holder.txtStatus.setText("Status: "+inquiriesList.get(position).getPropertyStatus());
        }
        else if(type.equals("realesate")) {
            holder.txtInfo1.setText("Assumer: "+inquiriesList.get(position).getInfo1());
            holder.txtInfo2.setText("Contactno: "+inquiriesList.get(position).getInfo2());
            holder.txtStatus.setText("Status: "+inquiriesList.get(position).getPropertyStatus());
        }


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

        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                int menu = item.getItemId();
                int itemID = inquiriesList.get(pos).getItemID();
                int propertyID = inquiriesList.get(pos).getPropertyID();
                String propType = inquiriesList.get(pos).getType();

                switch (menu) {
                    case R.id.menuViewProperty:
                        Intent i_view_prop_details = new Intent(context, PropertyDetails.class);
                        i_view_prop_details.putExtra("itemID", itemID);
                        i_view_prop_details.putExtra("propID", propertyID);
                        i_view_prop_details.putExtra("propertyType", propType);
                        i_view_prop_details.putExtra("actionType", "remove-assumer-assumptions"); // for button text-only

                        context.startActivity(i_view_prop_details);
                    break;
                    case R.id.menuAssumerInfo:
                        Intent i_view_assumers_info = new Intent(context, AssumerInformation.class);
                        i_view_assumers_info.putExtra("propertyID", propertyID);
                        context.startActivity(i_view_assumers_info);
                    break;
                    case R.id.sendMessage:
//                        String outboundUser = inquiriesList.get(pos).getUserEmail();
//                        Intent i_open_chat_room = new Intent(context, ChatRoom.class);
//                        i_open_chat_room.putExtra("message_sender", outboundUser);
//                        context.startActivity(i_open_chat_room);
                        // UNUSED RIGHT NOW
                    break;
                }
                return false;
            }
        });

    }

    @Override
    public int getItemCount() {
        return inquiriesList.size();
    }
}