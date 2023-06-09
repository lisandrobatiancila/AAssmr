package com.example.a_assmr.Views.Credentials.Account.My_Property.ViewProperty.Vehicle;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.a_assmr.CommonDir.ActiveUserSharedPref;
import com.example.a_assmr.R;
import com.example.a_assmr.Views.Credentials.Account.My_Property.ViewProperty.ItemViewModel;
import com.example.a_assmr.Views.Credentials.Account.My_Property.ViewProperty.Vehicle.Controller.MyVehicleController;
import com.example.a_assmr.Views.Credentials.Account.My_Property.ViewProperty.Vehicle.Model.MyVehicleServerResponse;

public class MyVehicle extends Fragment{
    RecyclerView vehicleRV;
    MyVehicleAdapter vehicleAdapter;
    ActiveUserSharedPref sharedPref;
    MyVehicleController vehicleController;
    ItemViewModel itemViewModel;
    Context context;
    AlertDialog dialog;
    SwipeRefreshLayout swipeRefreshLayout, swipeRefreshLayout1;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.account_my_vehicle, container, false);
        initilizeDialog(view);

        initializeFields(view);

        return view;
    }
    private void initializeFields(View view) {
        vehicleRV = view.findViewById(R.id.rvMyVehicle);
        swipeRefreshLayout = view.findViewById(R.id.srlMyVehicle);

        sharedPref = new ActiveUserSharedPref(requireActivity());

        vehicleController = new MyVehicleController(requireActivity(), requireActivity(), dialog);
        vehicleController.fetchMyPostedVehicles(sharedPref.activeUserID());
        dialog.show();

        itemViewModel = new ViewModelProvider(requireActivity()).get(ItemViewModel.class);
        itemViewModel.getSelectedItem().observe(requireActivity(), item -> {
            Object obj = item.getCertainGenericClass();
            if(obj instanceof MyVehicleServerResponse) {
                vehicleAdapter = new MyVehicleAdapter(context, ((MyVehicleServerResponse) obj).myVehicleLists);

                vehicleRV.setLayoutManager(new LinearLayoutManager(context));
                vehicleRV.setAdapter(vehicleAdapter);
            }
        });

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.setRefreshing(false);
                initilizeDialog(view);
                initializeFields(view);
            }
        });
    }
    private void initilizeDialog(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        View view_dialog = getLayoutInflater().inflate(R.layout.progress_bar_1, null);
        builder.setView(view_dialog)
                .setCancelable(false);

        TextView txtTitle = view_dialog.findViewById(R.id.txtProgress1Title);
        swipeRefreshLayout1 = view_dialog.findViewById(R.id.signupRefresh);

        txtTitle.setText("My Vehicle properties.");
        dialog = builder.create();

        swipeRefreshLayout1.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout1.setRefreshing(false);
                dialog.dismiss();
            }
        });
    }
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;
    }
}
