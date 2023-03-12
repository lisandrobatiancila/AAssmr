package com.example.a_assmr.Views.Credentials.Properties.Fragments.Vehicle;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.a_assmr.Common;
import com.example.a_assmr.R;
import com.example.a_assmr.Views.Credentials.Properties.Fragments.Vehicle.Interface.VehicleInterface;
import com.example.a_assmr.Views.Credentials.Properties.Fragments.Vehicle.VehicleController.VehiclePropController;
import com.example.a_assmr.Views.Credentials.Properties.Fragments.Vehicle.VehicleModel.VehicleServerRequest;
import com.example.a_assmr.Views.Credentials.Properties.Fragments.Vehicle.VehicleModel.VehicleServerResponse;
import com.example.a_assmr.Views.Credentials.Properties.Fragments.Vehicle.VehicleModel.Vehicles;

import java.util.List;

public class VehicleFragment extends Fragment implements VehicleInterface {
    RecyclerView vehicleRV;
    SwipeRefreshLayout swipeRefreshLayout;
    private int defaultPropID = 0;
    private Common common;
    AlertDialog dialog;
    TextView txtProgress1Title;
    @SuppressLint("MissingInflatedId")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = getLayoutInflater().inflate(R.layout.prop_vehicle_root_fragment, container, false);
        vehicleRV = view.findViewById(R.id.rvPropVehicle);
        swipeRefreshLayout = view.findViewById(R.id.propVSwipeRefresh);

        setUpProgressBar();
        dialog.show();
        VehiclePropController vehiclePropController = new VehiclePropController(getActivity().getApplicationContext(), this);
        vehiclePropController.getVehicleProperties(new VehicleServerRequest(0));

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                dialog.show();
                swipeRefreshLayout.setRefreshing(false);
                vehiclePropController.getVehicleProperties(new VehicleServerRequest(0));
            }
        });
        return view;
    }
    private void setUpProgressBar() {
        View modalView = getLayoutInflater().inflate(R.layout.progress_bar_1, null);
        SwipeRefreshLayout sRL = modalView.findViewById(R.id.signupRefresh);
        txtProgress1Title = modalView.findViewById(R.id.txtProgress1Title);
        txtProgress1Title.setText("Fetching Vehicle properties...");
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setView(modalView).setCancelable(false);

        dialog = builder.create();
        sRL.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                sRL.setRefreshing(false);
                dialog.dismiss();
            }
        });
    }
    @Override
    public void propertyLists(List<Vehicles> vehiclesList) {
        dialog.dismiss();
        VehicleRVAdapter Vadapter = new VehicleRVAdapter(getActivity().getApplicationContext(), vehiclesList);
        vehicleRV.setLayoutManager(new LinearLayoutManager(getContext()));
        vehicleRV.setAdapter(Vadapter);
    }
}
