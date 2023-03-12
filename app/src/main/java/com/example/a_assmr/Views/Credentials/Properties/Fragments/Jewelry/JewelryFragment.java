package com.example.a_assmr.Views.Credentials.Properties.Fragments.Jewelry;

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

import com.example.a_assmr.R;
import com.example.a_assmr.Views.Credentials.Properties.Fragments.Jewelry.JewelryController.JewelryController;
import com.example.a_assmr.Views.Credentials.Properties.Fragments.Jewelry.JewelryInterface.JewelryInterface;
import com.example.a_assmr.Views.Credentials.Properties.Fragments.Jewelry.JewelryModel.Jewelries;

import java.util.List;

public class JewelryFragment extends Fragment implements JewelryInterface {
    RecyclerView jewelryRV;
    JewelryController controller;
    SwipeRefreshLayout mainRefreshLayout;
    AlertDialog dialog;
    @SuppressLint("MissingInflatedId")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.prop_jewelry_root_fragment, container, false);
        jewelryRV = view.findViewById(R.id.rvPropJewelry);
        mainRefreshLayout = view.findViewById(R.id.propJSwipeRefresh);

        setUpProgressBar();
        dialog.show();

        controller = new JewelryController(getContext(), this);
        controller.getJewelryLists(0);

        mainRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                dialog.show();
                mainRefreshLayout.setRefreshing(false);
                controller.getJewelryLists(0);
            }
        });
        return view;
    }
    private void setUpProgressBar() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        View view = getLayoutInflater().inflate(R.layout.progress_bar_1, null);
        TextView txtTitle = view.findViewById(R.id.txtProgress1Title);
        SwipeRefreshLayout refreshLayout = view.findViewById(R.id.signupRefresh);

        txtTitle.setText("Fetching Jewelry properties...");
        builder.setView(view).setCancelable(false);
        dialog = builder.create();

        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshLayout.setRefreshing(false);
                dialog.dismiss();
            }
        });
    }
    @Override
    public void getJewelryLists(List<Jewelries> jewelryLists) {
        dialog.dismiss();

        JewelryRVAdapter adapter = new JewelryRVAdapter(getContext(), jewelryLists);
        jewelryRV.setLayoutManager(new LinearLayoutManager(getContext()));
        jewelryRV.setAdapter(adapter);
    }
}