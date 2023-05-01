package com.example.a_assmr.Views.Credentials.Account.My_Property.Inquiries;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.a_assmr.R;
import com.example.a_assmr.Views.Credentials.Account.My_Property.Inquiries.MyAssumptions.MyAssumptions;

public class Inquiries extends Fragment {
    Context context;
    Button btnMyAssumptions, btnInquiredProperty;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.account_inquiries, container, false);

        initForm(view);
        setView("my-assumptions");
        return view;
    }

    private void initForm(View view) {
        btnMyAssumptions = view.findViewById(R.id.btnMyAssumptions);
        btnInquiredProperty = view.findViewById(R.id.btnMyInquiredProperty);

        btnMyAssumptions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setView("my-assumptions");
            }
        });
        btnInquiredProperty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setView("inquired-property");
            }
        });
    }
    private void setView(String curr_view) {
        switch (curr_view) {
            case "my-assumptions":
                btnInquiredProperty.setBackgroundColor(getResources().getColor(R.color.purpleColor));
                btnMyAssumptions.setBackgroundColor(getResources().getColor(R.color.assmrColor));
                getChildFragmentManager().beginTransaction().replace(R.id.accntFrameLayout, new MyAssumptions()).commit();
            break;
            case "inquired-property":
                btnMyAssumptions.setBackgroundColor(getResources().getColor(R.color.purpleColor));
                btnInquiredProperty.setBackgroundColor(getResources().getColor(R.color.assmrColor));
            break;
        }
    }
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;
    }
}
