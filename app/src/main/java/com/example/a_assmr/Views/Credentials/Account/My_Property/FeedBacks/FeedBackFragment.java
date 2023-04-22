package com.example.a_assmr.Views.Credentials.Account.My_Property.FeedBacks;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.a_assmr.CommonDir.ActiveUserSharedPref;
import com.example.a_assmr.CommonDir.GenericClass;
import com.example.a_assmr.CommonDir.StandardResponse;
import com.example.a_assmr.R;
import com.example.a_assmr.Views.Credentials.Account.My_Property.FeedBacks.Controller.FeedBackController;
import com.example.a_assmr.Views.Credentials.Account.My_Property.FeedBacks.Interface.FeedBackInterface;
import com.example.a_assmr.Views.Credentials.Account.My_Property.FeedBacks.Model.FeedBackForm;
import com.example.a_assmr.Views.Credentials.Account.My_Property.FeedBacks.Model.FeedBackServerResponse;
import com.example.a_assmr.Views.Credentials.Account.My_Property.ViewProperty.ItemViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class FeedBackFragment extends Fragment implements FeedBackInterface {
    FloatingActionButton fabOpenFeedBackFrom;
    AlertDialog dialog;
    Context context;
    ItemViewModel itemViewModel;
    SwipeRefreshLayout swipeRefreshLayout;
    RecyclerView rvFeedBacks;
    @SuppressLint("MissingInflatedId")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.account_feedbacks, container, false);

        initFields(view);
        getAllFeedBacks();

        fabOpenFeedBackFrom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                View view_feedback_from = getLayoutInflater().inflate(R.layout.account_feedback_form, container, false);
                builder.setView(view_feedback_from)
                        .setCancelable(false);

                EditText edtFeedBack = view_feedback_from.findViewById(R.id.edtFeedBack);
                RatingBar ratingBar = view_feedback_from.findViewById(R.id.ratingBar);

                Button btnSave, btnClose;
                btnSave = view_feedback_from.findViewById(R.id.btnSave);
                btnClose = view_feedback_from.findViewById(R.id.btnClose);

                dialog = builder.create();

                btnSave.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        final int userID = new ActiveUserSharedPref(context).activeUserID();
                        String feedback = edtFeedBack.getText().toString();
                        int rating = (int) (double) ratingBar.getRating();

                        if(!feedback.isEmpty()) {
                            FeedBackForm feedBackForm = new FeedBackForm(userID, feedback, rating);
                            FeedBackController controller = new FeedBackController(context, requireActivity());
                            controller.createFeedBack(feedBackForm);

                            itemViewModel.getSelectedItem().observe(requireActivity(), item -> {
                                Object obj = item.getCertainGenericClass();
                                if(obj instanceof StandardResponse) {
                                    dialog.dismiss();
                                    Toast.makeText(context, ((StandardResponse) obj).getMessage(), Toast.LENGTH_LONG).show();
                                }
                            });
                        }
                        else
                            Toast.makeText(context, "Empty fields.", Toast.LENGTH_SHORT).show();
                    }
                });
                btnClose.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });

                dialog.show();
            }
        });

        return view;
    }

    private void initFields(View view) {
        swipeRefreshLayout = view.findViewById(R.id.swipeRefreshLayout);
        rvFeedBacks = view.findViewById(R.id.rvFeedBack);
        fabOpenFeedBackFrom = view.findViewById(R.id.fabOpenFeedbackForm);
        itemViewModel = new ViewModelProvider(requireActivity()).get(ItemViewModel.class);
    }

    private void getAllFeedBacks() {
        FeedBackController feedBackController = new FeedBackController(context, requireActivity());
        feedBackController.getAllFeedBack();
        itemViewModel.getSelectedItem().observe(requireActivity(), item -> {
            Object obj = item.getCertainGenericClass();
            if(obj instanceof FeedBackServerResponse) {
                Toast.makeText(context, ((FeedBackServerResponse) obj).getStatus()+"", Toast.LENGTH_LONG).show();
                if(((FeedBackServerResponse) obj).getCode() == 200) {
                    FeedBackAdapter feedBackAdapter = new FeedBackAdapter(context, ((FeedBackServerResponse) obj).getUserFeedBacks());
                    rvFeedBacks.setLayoutManager(new LinearLayoutManager(context));
                    rvFeedBacks.setAdapter(feedBackAdapter);
                }
                else
                    Toast.makeText(context, ((FeedBackServerResponse) obj).getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public void getFeedBack(GenericClass genericClass) {
        Toast.makeText(context, "ok", Toast.LENGTH_LONG).show();
    }
}
