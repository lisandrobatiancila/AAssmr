package com.example.a_assmr.Views.Credentials.Account.My_Property.Inquiries.MyAssumptions.OwnerInformation;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.a_assmr.Common;
import com.example.a_assmr.R;
import com.example.a_assmr.Views.Credentials.Account.My_Property.Inquiries.MyAssumptions.Controller.MyAssumptionController;
import com.example.a_assmr.Views.Credentials.Account.My_Property.Inquiries.MyAssumptions.OwnerInformation.Model.OwnerModelContainer;
import com.example.a_assmr.Views.Credentials.Account.My_Property.ViewProperty.ItemViewModel;

public class OwnerInformation extends AppCompatActivity {
    MyAssumptionController myAssumptionController;
    Common common;
    ItemViewModel itemViewModel;
    TextView txtOwner, txtContactno, txtLocation, txtPostedProperty;
    Button btnSendMessage;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.accnt_inqrs_my_inqrs_owner_infrmtn);
        setTitle("Owner Information");

        initForm();

        itemViewModel.getSelectedItem().observe(this, item -> {
            Object obj = item.getCertainGenericClass();
            if(obj instanceof OwnerModelContainer) {
                if(((OwnerModelContainer) obj).getCode() == 200) {
                    txtOwner.setText("Owner: "+((OwnerModelContainer) obj).getOwnerInformation().get(0).getOwnerName());
                    txtContactno.setText("Contactno: "+((OwnerModelContainer) obj).getOwnerInformation().get(0).getOwnerContactno());
                    txtLocation.setText("Location: "+((OwnerModelContainer) obj).getOwnerInformation().get(0).getOwnerAddress());
                    txtPostedProperty.setText("Property Type: ");
                }
                else
                    Toast.makeText(this, "Something went wrong", Toast.LENGTH_LONG).show();
            }
        });

        btnSendMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
    private void initForm() {
        itemViewModel = new ViewModelProvider(this).get(ItemViewModel.class);

        Bundle bundle = getIntent().getExtras();
        int ownerID = bundle.getInt("userID");

        myAssumptionController = new MyAssumptionController(this, this);
        myAssumptionController.getOwnerInformation(ownerID);

        txtOwner = findViewById(R.id.txtOwner); txtContactno = findViewById(R.id.txtContactno);
        txtLocation = findViewById(R.id.txtLocation); txtPostedProperty = findViewById(R.id.txtPostedProperty);
        btnSendMessage = findViewById(R.id.btnSendMessage);
    }
}
