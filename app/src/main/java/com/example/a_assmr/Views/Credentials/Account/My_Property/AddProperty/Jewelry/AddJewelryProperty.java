package com.example.a_assmr.Views.Credentials.Account.My_Property.AddProperty.Jewelry;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.a_assmr.CommonDir.ActiveUserSharedPref;
import com.example.a_assmr.CommonDir.GenericClassInterface;
import com.example.a_assmr.R;
import com.example.a_assmr.Views.Credentials.Account.My_Property.AddProperty.Jewelry.Controller.AddJewelryController;
import com.example.a_assmr.Views.Credentials.Account.My_Property.AddProperty.Jewelry.Interface.AddJewelryInterface;
import com.example.a_assmr.Views.Credentials.Account.My_Property.AddProperty.Jewelry.Model.AddJewelryForm;
import com.google.android.material.textfield.TextInputEditText;

public class AddJewelryProperty extends Fragment {
    TextInputEditText edtOwner, edtMobileNo, edtJewelryName, edtJewelryModel, edtLocation;
    TextInputEditText edtDownpayment, edtInstallmentpaid, edtInstallmentduration;
    TextInputEditText edtDelinquent, edtDescription;
    TextView txtFileCount;
    Button btnSave, btnReset;
    AddJewelryInterface addJewelryInterface;
    AddJewelryController addJewelryController;
    GenericClassInterface genericClassInterface;
    Intent imageData = null;
    AlertDialog dialog;
    SwipeRefreshLayout swipeRefreshLayout;
    ImageButton imageButton;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.account_add_jewelry_property, container, false);
        initializeForm(view);
        setUpProgressBar();

        ActivityResultLauncher jewelryLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @SuppressLint("ResourceAsColor")
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        imageData = result.getData();
                        if(imageData.getClipData() != null) {
                            txtFileCount.setText("You include ( "+imageData.getClipData().getItemCount()+" ) file(s)");
                            txtFileCount.setTextColor(R.color.teal_700);
                        }
                        else if(imageData.getData() != null) {
                            txtFileCount.setText("You include 1 file");
                            txtFileCount.setTextColor(R.color.teal_700);
                        }
                        else
                            Toast.makeText(getContext(), "Please select an image.", Toast.LENGTH_LONG).show();
                    }
                }
        );

        addJewelryController = new AddJewelryController(getContext(), addJewelryInterface, this, genericClassInterface);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    String owner = edtOwner.getText().toString();
                    String cellNo = edtMobileNo.getText().toString();
                    String jewelryName = edtJewelryName.getText().toString();
                    String jewelryModel = edtJewelryModel.getText().toString();
                    String location = edtLocation.getText().toString();
                    Double downpayment = Double.valueOf(edtDownpayment.getText().toString());
                    Double installmentpaid = Double.valueOf(edtInstallmentpaid.getText().toString());
                    String installmentduration = edtInstallmentduration.getText().toString();
                    String delinquent = edtDelinquent.getText().toString();
                    String description = edtDescription.getText().toString();

                    if(imageData != null) {
                        AddJewelryForm addJewelryForm = new AddJewelryForm(owner, cellNo, jewelryName, jewelryModel, location, downpayment, installmentpaid, installmentduration, delinquent, description);
                        ActiveUserSharedPref sharedPref = new ActiveUserSharedPref(getContext());
                        dialog.setCancelable(false);
                        dialog.show();

                        addJewelryController.saveJewelryInfo(addJewelryForm, sharedPref.activeUserID(), dialog, imageData);
                    }
                    else
                        Toast.makeText(getContext(), "Please provide an image.", Toast.LENGTH_LONG).show();
                }
                catch (Exception e) {
                    Toast.makeText(getContext(), "Please input a valid entry.", Toast.LENGTH_LONG).show();
                }
            }
        });
        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetForm();
            }
        });

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkPermission()) {
                    Intent i_open_gallery = new Intent(Intent.ACTION_GET_CONTENT);
                    i_open_gallery.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
                    i_open_gallery.setType("image/*");

                    jewelryLauncher.launch(i_open_gallery);
                }
                else
                    ActivityCompat.requestPermissions((Activity) getContext(), new String[] {Manifest.permission.WRITE_EXTERNAL_STORAGE}, 101);
            }
        });
        return view;
    }
    private void initializeForm(View view) {
        edtOwner = view.findViewById(R.id.edtOwner);
        edtMobileNo = view.findViewById(R.id.edtCellNo);
        edtJewelryName = view.findViewById(R.id.edtName);
        edtJewelryModel = view.findViewById(R.id.edtModel);
        edtLocation = view.findViewById(R.id.edtLocation);
        edtDownpayment = view.findViewById(R.id.edtDownpayment);
        edtInstallmentpaid = view.findViewById(R.id.edtInstallmentpaid);
        edtInstallmentduration = view.findViewById(R.id.edtInstallmentduration);
        edtDelinquent = view.findViewById(R.id.edtDelinquent);
        edtDescription = view.findViewById(R.id.edtDescription);

        txtFileCount = view.findViewById(R.id.txtFileCount);

        btnSave = view.findViewById(R.id.btnSave);
        btnReset = view.findViewById(R.id.btnReset);
        imageButton = view.findViewById(R.id.imgbtnOpenGallery);
    }
    @SuppressLint("ResourceAsColor")
    public void resetForm() {
        edtOwner.setText("");edtMobileNo.setText("");edtJewelryName.setText("");edtJewelryModel.setText("");
        edtLocation.setText("");edtDownpayment.setText("");edtInstallmentpaid.setText("");edtInstallmentduration.setText("");
        edtDelinquent.setText(""); edtDescription.setText("");
        txtFileCount.setText("Please include a file *");
        txtFileCount.setTextColor(R.color.red);
        imageData = null;
    }
    private void setUpProgressBar() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        View view = getLayoutInflater().inflate(R.layout.progress_bar_1, null);
        TextView txtTitle = view.findViewById(R.id.txtProgress1Title);

        swipeRefreshLayout = view.findViewById(R.id.signupRefresh);
        txtTitle.setText("Uploading...");
        builder.setView(view);
        dialog = builder.create();

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.setRefreshing(false);
                dialog.dismiss();
            }
        });
    }
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        addJewelryInterface = (AddJewelryInterface) context;
        genericClassInterface = (GenericClassInterface) context;
    }
    private boolean checkPermission() {
        boolean result = ContextCompat.checkSelfPermission(getContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED;
        return result;
    }
}
