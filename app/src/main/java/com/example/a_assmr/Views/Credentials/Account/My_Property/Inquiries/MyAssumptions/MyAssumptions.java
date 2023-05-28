package com.example.a_assmr.Views.Credentials.Account.My_Property.Inquiries.MyAssumptions;

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
import com.example.a_assmr.R;
import com.example.a_assmr.Views.Credentials.Account.My_Property.Inquiries.MyAssumptions.Controller.MyAssumptionController;
import com.example.a_assmr.Views.Credentials.Account.My_Property.Inquiries.MyAssumptions.Model.InquiriesAssumptionModel;
import com.example.a_assmr.Views.Credentials.Account.My_Property.Inquiries.MyAssumptions.Model.MyAssumptionsModel;
import com.example.a_assmr.Views.Credentials.Account.My_Property.ViewProperty.ItemViewModel;

import java.util.ArrayList;
import java.util.List;

public class MyAssumptions extends Fragment {
    Context context;
    RecyclerView rv;
    SwipeRefreshLayout swipeRefreshLayout;
    MyAssumptionController myAssumptionController;
    ItemViewModel itemViewModel;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.accnt_inqrs_my_assumptions, container, false);

        initForm(view);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.setRefreshing(false);
                myAssumptionController.getMyAssumedProperties(new ActiveUserSharedPref(context).activeUserID());
            }
        });
        itemViewModel.getSelectedItem().observe(requireActivity(), item -> {
            Object obj = item.getCertainGenericClass();
            if(obj instanceof MyAssumptionsModel) {
                if(((MyAssumptionsModel) obj).getCode() == 200) {
                    List<InquiriesAssumptionModel> assumptionLists = new ArrayList<>();
                    int size = ((MyAssumptionsModel) obj).getMyassumptions().size();

                    for(int i = 0; i < size; i++) {
                        int userID = ((MyAssumptionsModel) obj).getMyassumptions().get(i).getUserID();
                        int ID = ((MyAssumptionsModel) obj).getMyassumptions().get(i).getID();
                        int propID = ((MyAssumptionsModel) obj).getMyassumptions().get(i).getPropID();
                        String userEmail = ((MyAssumptionsModel) obj).getMyassumptions().get(i).getUserEmail();
                        String info1 = ((MyAssumptionsModel) obj).getMyassumptions().get(i).getInfo1();
                        String info2 = ((MyAssumptionsModel) obj).getMyassumptions().get(i).getInfo2();
                        String info3 = ((MyAssumptionsModel) obj).getMyassumptions().get(i).getInfo3();
                        String info4 = ((MyAssumptionsModel) obj).getMyassumptions().get(i).getInfo4();
                        String info5 = ((MyAssumptionsModel) obj).getMyassumptions().get(i).getInfo5();
                        String info6 = ((MyAssumptionsModel) obj).getMyassumptions().get(i).getInfo6();

                        assumptionLists.add(new InquiriesAssumptionModel(userID, ID, propID,userEmail , info1, info2, info3, info4, info5, info6));
                    }
                    MyAssumptionAdapter adapter = new MyAssumptionAdapter(context, assumptionLists);
                    rv.setLayoutManager(new LinearLayoutManager(context));
                    rv.setAdapter(adapter);
                }
                else
                    Toast.makeText(context, "Something went wrong!", Toast.LENGTH_LONG).show();
            }
        });
        return view;
    }

    private void initForm(View view) {
        rv = view.findViewById(R.id.rvInquiries);
        swipeRefreshLayout = view.findViewById(R.id.swipeRefreshLayout);

        itemViewModel = new ViewModelProvider(requireActivity()).get(ItemViewModel.class);

        myAssumptionController = new MyAssumptionController(context, requireActivity());
        myAssumptionController.getMyAssumedProperties(new ActiveUserSharedPref(context).activeUserID());
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;
    }
}
