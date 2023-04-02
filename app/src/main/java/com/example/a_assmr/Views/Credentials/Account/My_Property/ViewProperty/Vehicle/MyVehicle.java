package com.example.a_assmr.Views.Credentials.Account.My_Property.ViewProperty.Vehicle;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.account_my_vehicle, container, false);
        initializeFields(view);

        return view;
    }
    private void initializeFields(View view) {
        vehicleRV = view.findViewById(R.id.rvMyVehicle);

        sharedPref = new ActiveUserSharedPref(requireActivity());

        vehicleController = new MyVehicleController(requireActivity(), requireActivity());
        vehicleController.fetchMyPostedVehicle(sharedPref.activeUserID());

        itemViewModel = new ViewModelProvider(requireActivity()).get(ItemViewModel.class);
        itemViewModel.getSelectedItem().observe(requireActivity(), item -> {
            Object obj = item.getCertainGenericClass();
            if(obj instanceof MyVehicleServerResponse) {
                vehicleAdapter = new MyVehicleAdapter(context, ((MyVehicleServerResponse) obj).myVehicleLists);

                vehicleRV.setLayoutManager(new LinearLayoutManager(context));
                vehicleRV.setAdapter(vehicleAdapter);
            }
        });
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;
    }
}
