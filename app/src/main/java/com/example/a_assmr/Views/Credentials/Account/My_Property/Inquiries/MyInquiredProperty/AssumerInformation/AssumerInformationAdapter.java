package com.example.a_assmr.Views.Credentials.Account.My_Property.Inquiries.MyInquiredProperty.AssumerInformation;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a_assmr.Common;
import com.example.a_assmr.CommonDir.StandardResponse;
import com.example.a_assmr.R;
import com.example.a_assmr.Views.Credentials.Account.My_Property.Inquiries.MyInquiredProperty.AssumerInformation.Model.AssumerInformationModel;
import com.example.a_assmr.Views.Credentials.Account.My_Property.Inquiries.MyInquiredProperty.Controller.MyInquiriesController;
import com.example.a_assmr.Views.Credentials.Account.My_Property.Messages.ChatRoom.ChatRoom;
import com.example.a_assmr.Views.Credentials.Account.My_Property.ViewProperty.ItemViewModel;

import java.util.List;

public class AssumerInformationAdapter extends RecyclerView.Adapter<AssumerInformationHolder> {
    Context context;
    LayoutInflater inflater;
    Common common;
    List<AssumerInformationModel> assumerInformationModelList;
    ItemViewModel itemViewModel;
    public AssumerInformationAdapter(Context context, List<AssumerInformationModel> assumerInformationModelList) {
        this.context = context;
        this.common = new Common();
        this.inflater = LayoutInflater.from(context);
        this.assumerInformationModelList = assumerInformationModelList;
        this.itemViewModel = new ViewModelProvider((ViewModelStoreOwner) this.context).get(ItemViewModel.class);
    }

    @NonNull
    @Override
    public AssumerInformationHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new AssumerInformationHolder(inflater.inflate(R.layout.accnt_inqrs_my_inqrs_assmr_infrmtn_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull AssumerInformationHolder holder, int position) {
        final int pos = position;

        holder.txtFullname.setText("Full Name: "+assumerInformationModelList.get(pos).getFullName());
        holder.txtAssmrIncome.setText("Income: "+assumerInformationModelList.get(pos).getAssumer_income());
        holder.txtAssmrWork.setText("Work: "+assumerInformationModelList.get(pos).getAssumer_work());
        holder.txtAddress.setText("Address: "+assumerInformationModelList.get(pos).getAssumer_address());
        holder.txtDateOfAssmptn.setText("Date Of Assmptn: "+assumerInformationModelList.get(pos).getTransaction_date());


        holder.btnCancelAssmptn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try{
                    new AlertDialog.Builder(context)
                            .setTitle("Confirm Action")
                            .setMessage("Are you sure you want to cancel '"+assumerInformationModelList.get(pos).getFullName()+"' assumption?")
                            .setIcon(R.drawable.baseline_error_24)
                            .setCancelable(false)
                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    final int assumerID = assumerInformationModelList.get(pos).getAssumerID();
                                    MyInquiriesController inquiriesController = new MyInquiriesController(context, (FragmentActivity) context);
                                    inquiriesController.ownerCancelAssumerAssumptions(assumerID);
                                    assumerInformationModelList.remove(pos);
                                    notifyItemRemoved(holder.getAdapterPosition());
                                    notifyDataSetChanged();

                                    itemViewModel.getSelectedItem().observe((LifecycleOwner) context, object-> {
                                        Object obj = object.getCertainGenericClass();
                                        if(obj instanceof StandardResponse) {
                                            Toast.makeText(context, ((StandardResponse) obj).getMessage(), Toast.LENGTH_SHORT).show();
                                        }
                                    });
                                }
                            })
                            .setNegativeButton(android.R.string.no, null)
                            .show();
                }
                catch(Exception exception) {
                    Toast.makeText(context, exception.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });

        holder.btnSendMssg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String outboundUser = assumerInformationModelList.get(pos).getUserEmail();
                Intent i_open_chat_room = new Intent(context, ChatRoom.class);
                i_open_chat_room.putExtra("message_sender", outboundUser);
                context.startActivity(i_open_chat_room);
            }
        });
    }

    @Override
    public int getItemCount() {
        return assumerInformationModelList.size();
    }
}
