package com.example.a_assmr.Views.Credentials.Account.My_Property.ViewProperty.Realestate;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.a_assmr.CommonDir.ActiveUserSharedPref;
import com.example.a_assmr.R;
import com.example.a_assmr.Views.Credentials.Account.My_Property.ViewProperty.ItemViewModel;
import com.example.a_assmr.Views.Credentials.Account.My_Property.ViewProperty.Realestate.Controller.MyRealestateController;
import com.example.a_assmr.Views.Credentials.Account.My_Property.ViewProperty.Realestate.Model.MyRealestateServerResponse;

public class MyRealestate extends Fragment {
    SwipeRefreshLayout swipeRefreshLayout1;
    SwipeRefreshLayout swipeRefreshLayout2;
    RecyclerView rvRealestate;
    AlertDialog dialog;
    ItemViewModel itemViewModel;
    MyRealestateController controller;
    Context context;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.account_my_realestate, container, false);
        initializedDialog(view);
        initializedFields(view);

        return view;
    }

    public void initializedDialog(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        View view_dialog = getLayoutInflater().inflate(R.layout.progress_bar_1, null);
        builder.setView(view_dialog);

        swipeRefreshLayout2 = view_dialog.findViewById(R.id.signupRefresh);
        TextView txtTitle = view_dialog.findViewById(R.id.txtProgress1Title);
        txtTitle.setText("My Realestate properties.");
        swipeRefreshLayout2.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout2.setRefreshing(false);
                dialog.dismiss();
            }
        });
        dialog = builder.create();

        builder.setCancelable(false);
        dialog.show();
    }
    public void initializedFields(View view) {
        ActiveUserSharedPref sharedPref = new ActiveUserSharedPref(requireActivity());

        swipeRefreshLayout1 = view.findViewById(R.id.srlMyRealestate);
        rvRealestate = view.findViewById(R.id.rvMyRealestate);
        controller = new MyRealestateController(requireActivity(), requireActivity(), dialog);
        controller.fetchMyPostedRealestates(sharedPref.activeUserID());

        swipeRefreshLayout1.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout1.setRefreshing(false);
                dialog.show();
                controller.fetchMyPostedRealestates(sharedPref.activeUserID());
            }
        });

        itemViewModel = new ViewModelProvider(requireActivity()).get(ItemViewModel.class);
        itemViewModel.getSelectedItem().observe(requireActivity(), item -> {
            Object obj = item.getCertainGenericClass();
            if(obj instanceof MyRealestateServerResponse) {
                MyRealestateAdapter realestateAdapter = new MyRealestateAdapter(context, ((MyRealestateServerResponse) obj).myRealestateLists);
                rvRealestate.setLayoutManager(new LinearLayoutManager(context));
                rvRealestate.setAdapter(realestateAdapter);
            }
        });
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;
    }
}
