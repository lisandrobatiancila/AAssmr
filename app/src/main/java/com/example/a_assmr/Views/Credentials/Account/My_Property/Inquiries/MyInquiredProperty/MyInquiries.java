package com.example.a_assmr.Views.Credentials.Account.My_Property.Inquiries.MyInquiredProperty;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.a_assmr.CommonDir.ActiveUserSharedPref;
import com.example.a_assmr.CommonDir.StandardResponse;
import com.example.a_assmr.R;
import com.example.a_assmr.Views.Credentials.Account.My_Property.Inquiries.MyInquiredProperty.Controller.MyInquiriesController;
import com.example.a_assmr.Views.Credentials.Account.My_Property.Inquiries.MyInquiredProperty.Model.MyInquiryModel;
import com.example.a_assmr.Views.Credentials.Account.My_Property.ViewProperty.ItemViewModel;

public class MyInquiries extends Fragment {
    SwipeRefreshLayout swipeRefreshLayout;
    RecyclerView rv;
    MyInquiriesController myInquiriesController;
    Context context;
    ItemViewModel itemViewModel;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.accnt_inqrs_my_inquires, container, false);

        initFields(view);
        fetchRecord();

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.setRefreshing(false);
                fetchRecord();
            }
        });
        return view;
    }

    private void initFields(View view) {
        itemViewModel = new ViewModelProvider(requireActivity()).get(ItemViewModel.class);

        rv = view.findViewById(R.id.rvMyInquiries);
        swipeRefreshLayout = view.findViewById(R.id.swipeRefreshLayout);

        myInquiriesController = new MyInquiriesController(context, requireActivity());
    }
    private void fetchRecord() {
        myInquiriesController.getMyInquiredProperty(new ActiveUserSharedPref(context).activeUserID());

        itemViewModel.getSelectedItem().observe(requireActivity(), item -> {
            Object obj = item.getCertainGenericClass();
            if(obj instanceof MyInquiryModel) {
                if(((MyInquiryModel) obj).getCode() == 200) {
                    rv.setLayoutManager(new LinearLayoutManager(context));
                    MyInquiriesAdapter adapter = new MyInquiriesAdapter(context, ((MyInquiryModel) obj).getInquiries());
                    rv.setAdapter(adapter);
                }
                else
                    Toast.makeText(context, "Something went wrong", Toast.LENGTH_LONG).show();
            }
            else if(obj instanceof StandardResponse) {
                if(((StandardResponse) obj).getCode() == 200)
                    Toast.makeText(context, "Removing was successful.", Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(context, "Cancellation has failed.", Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;
    }
}
