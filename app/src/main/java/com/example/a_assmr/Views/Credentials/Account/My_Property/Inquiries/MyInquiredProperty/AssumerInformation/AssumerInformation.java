package com.example.a_assmr.Views.Credentials.Account.My_Property.Inquiries.MyInquiredProperty.AssumerInformation;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.a_assmr.R;
import com.example.a_assmr.Views.Credentials.Account.My_Property.Inquiries.MyInquiredProperty.AssumerInformation.Model.AssumerInformationModel;
import com.example.a_assmr.Views.Credentials.Account.My_Property.Inquiries.MyInquiredProperty.AssumerInformation.Model.AssumerInformationModelContainer;
import com.example.a_assmr.Views.Credentials.Account.My_Property.Inquiries.MyInquiredProperty.Controller.MyInquiriesController;
import com.example.a_assmr.Views.Credentials.Account.My_Property.ViewProperty.ItemViewModel;

public class AssumerInformation extends AppCompatActivity {
    private int assumerID, propertyID;
    MyInquiriesController myInquiriesController;
    SwipeRefreshLayout swipeRefreshLayout;
    RecyclerView rvAssmrLists;
    ItemViewModel itemViewModel;
    TextView txtTotalAssumer;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.accnt_inqrs_my_inqrs_assmr_infrmtn);

        setTitle("Assumer Information");

        initForm();

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.setRefreshing(false);
                fetchRecords();
            }
        });
    }

    private void initForm() {
        itemViewModel = new ViewModelProvider(this).get(ItemViewModel.class);

        Bundle bundle = getIntent().getExtras();
        propertyID = bundle.getInt("propertyID");

        swipeRefreshLayout = findViewById(R.id.swipeRefreshLayout);
        rvAssmrLists = findViewById(R.id.rvAssmrLists);
        txtTotalAssumer = findViewById(R.id.txtTotalAssmr);

        myInquiriesController = new MyInquiriesController(this, this);
        fetchRecords();
    }
    private void fetchRecords() {
        myInquiriesController.getAssumerLists(propertyID);
        itemViewModel.getSelectedItem().observe(this, object-> {
            Object obj = object.getCertainGenericClass();
            if(obj instanceof AssumerInformationModelContainer) {
                txtTotalAssumer.setText("Total Assumer: 0");
                if(((AssumerInformationModelContainer) obj).getCode() == 200 && ((AssumerInformationModelContainer) obj).getAssumerLists().size() > 0) {
                    txtTotalAssumer.setText("Total Assumer: "+String.valueOf(((AssumerInformationModelContainer) obj).getAssumerLists().get(0).getTotalAssumer()));

                    AssumerInformationAdapter adapter = new AssumerInformationAdapter(this, ((AssumerInformationModelContainer) obj).getAssumerLists());
                    rvAssmrLists.setLayoutManager(new LinearLayoutManager(this));
                    rvAssmrLists.setAdapter(adapter);
                }
                else if(((AssumerInformationModelContainer) obj).getCode() != 200)
                    Toast.makeText(this, ((AssumerInformationModelContainer) obj).getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}