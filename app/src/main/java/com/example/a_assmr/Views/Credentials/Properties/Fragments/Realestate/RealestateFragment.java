package com.example.a_assmr.Views.Credentials.Properties.Fragments.Realestate;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.a_assmr.R;
import com.example.a_assmr.Views.Credentials.Properties.Fragments.Realestate.RealestateController.RealestateController;
import com.example.a_assmr.Views.Credentials.Properties.Fragments.Realestate.RealestateInterface.RealestateInterface;
import com.example.a_assmr.Views.Credentials.Properties.Fragments.Realestate.RealestateModel.Realestate;

import java.util.List;

public class RealestateFragment extends Fragment implements RealestateInterface {
    RecyclerView realestateRV;
    SwipeRefreshLayout mainRefreshLayout;
    AlertDialog dialog;
    RealestateController realestateController;
    @SuppressLint("MissingInflatedId")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.prop_realestate_root_fragment, container, false);
        realestateRV = view.findViewById(R.id.rvPropRealEstate);
        mainRefreshLayout = view.findViewById(R.id.propRSwipeRefresh);

        setUpProgressBar();
        dialog.show();

        realestateController = new RealestateController(getContext(), this);
        realestateController.getRealestateLists(0);

        mainRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                dialog.show();
                realestateController.getRealestateLists(0);
                mainRefreshLayout.setRefreshing(false);
            }
        });
        return view;
    }

    @Override
    public void getRealestateLists(List<Realestate> realestateList) {
        dialog.dismiss();
        RealestateRVAdapter adapter = new RealestateRVAdapter(getContext(), realestateList);
        realestateRV.setLayoutManager(new LinearLayoutManager(getContext()));
        realestateRV.setAdapter(adapter);
    }
    private void setUpProgressBar() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        View view = getLayoutInflater().inflate(R.layout.progress_bar_1, null);
        TextView txtTitle = view.findViewById(R.id.txtProgress1Title);
        SwipeRefreshLayout swipeRefreshLayout = view.findViewById(R.id.signupRefresh);

        txtTitle.setText("Fetching Realestate properties...");
        builder.setView(view).setCancelable(false);
        dialog = builder.create();

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                dialog.dismiss();
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }
}
