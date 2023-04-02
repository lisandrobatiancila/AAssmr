package com.example.a_assmr.Views.Credentials.Account.My_Property.ViewProperty.Jewelry;

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
import com.example.a_assmr.Views.Credentials.Account.My_Property.ViewProperty.Jewelry.Controller.MyJewelryController;
import com.example.a_assmr.Views.Credentials.Account.My_Property.ViewProperty.Jewelry.Model.MyJewelryServerResponse;

public class MyJewelry extends Fragment {
    RecyclerView jewelryRV;
    SwipeRefreshLayout swipeRefreshLayout, swipeRefreshLayout1;
    MyJewelryController jewelryController;
    Context context;
    ActiveUserSharedPref sharedPref;
    ItemViewModel itemViewModel;
    MyJewelryAdapter jewelryAdapter;
    AlertDialog dialog;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.account_my_jewelry, container, false);

        initializeDialog(view);
        initializeFields(view);

        return view;
    }
    public void initializeFields(View view) {
        jewelryRV = view.findViewById(R.id.rvMyJewelry);
        swipeRefreshLayout = view.findViewById(R.id.srlMyJewelry);

        sharedPref = new ActiveUserSharedPref(context);

        itemViewModel = new ViewModelProvider(requireActivity()).get(ItemViewModel.class);

        jewelryController = new MyJewelryController(context, requireActivity(), dialog);
        jewelryController.fetchMyPostedJewelries(sharedPref.activeUserID());

        itemViewModel.getSelectedItem().observe(requireActivity(), item -> {
            Object obj = item.getCertainGenericClass();

            if(obj instanceof MyJewelryServerResponse) {
                jewelryAdapter = new MyJewelryAdapter(context, ((MyJewelryServerResponse) obj).myJewelryLists);

                jewelryRV.setLayoutManager(new LinearLayoutManager(context));
                jewelryRV.setAdapter(jewelryAdapter);
            }
        });

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.setRefreshing(false);
                dialog.dismiss();
                initializeDialog(view);
                initializeFields(view);
            }
        });
    }
    public void initializeDialog(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        View view_dialog = getLayoutInflater().inflate(R.layout.progress_bar_1, null);

        builder.setView(view_dialog)
                .setCancelable(false);
        dialog = builder.create();
        dialog.show();

        TextView txtTitle = view_dialog.findViewById(R.id.txtProgress1Title);
        swipeRefreshLayout1 = view_dialog.findViewById(R.id.signupRefresh);

        txtTitle.setText("My Jewelry properties.");
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
