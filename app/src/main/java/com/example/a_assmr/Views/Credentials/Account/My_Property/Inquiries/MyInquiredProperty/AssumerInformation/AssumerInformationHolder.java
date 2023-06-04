package com.example.a_assmr.Views.Credentials.Account.My_Property.Inquiries.MyInquiredProperty.AssumerInformation;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a_assmr.R;

public class AssumerInformationHolder extends RecyclerView.ViewHolder {
    TextView txtFullname, txtAssmrIncome, txtAssmrWork, txtAddress, txtDateOfAssmptn;
    Button btnCancelAssmptn, btnSendMssg;
    public AssumerInformationHolder(@NonNull View itemView) {
        super(itemView);

        txtFullname = itemView.findViewById(R.id.txtFullname);
        txtAssmrIncome = itemView.findViewById(R.id.txtAssmrIncome);
        txtAssmrWork = itemView.findViewById(R.id.txtAssmrWork);
        txtAddress = itemView.findViewById(R.id.txtAddress);
        txtDateOfAssmptn = itemView.findViewById(R.id.txtDateOfAssmptn);

        btnCancelAssmptn = itemView.findViewById(R.id.btnCancelAssmptn);
        btnSendMssg = itemView.findViewById(R.id.btnSendMessage);
    }
}
