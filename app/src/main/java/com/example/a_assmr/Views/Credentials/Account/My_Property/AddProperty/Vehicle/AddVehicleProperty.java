package com.example.a_assmr.Views.Credentials.Account.My_Property.AddProperty.Vehicle;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
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
import com.example.a_assmr.Views.Credentials.Account.My_Property.AddProperty.Vehicle.Controller.AddVehicleController;
import com.example.a_assmr.Views.Credentials.Account.My_Property.AddProperty.Vehicle.Interface.AddVehicleInterface;
import com.example.a_assmr.Views.Credentials.Account.My_Property.AddProperty.Vehicle.Model.AddVehicleForm;
import com.google.android.material.textfield.TextInputEditText;

public class AddVehicleProperty extends Fragment {
    ImageButton imageButton;
    TextView txtFileCount;
    Intent imageDATA = null;
    TextInputEditText edtOwner, edtCellNo;
    TextInputEditText edtBrand, edtModel, edtLocation, edtDownpayment;
    TextInputEditText edtInstallmentpaid, edtInstallmentduration, edtDelinquent, edtDescription;
    AddVehicleInterface addVehicleInterface;
    GenericClassInterface genericClassInterface;
    AddVehicleController addVehicleController;
    AlertDialog dialog;
    SwipeRefreshLayout swipeRefreshLayout;
    @SuppressLint("MissingInflatedId")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.account_add_vehicle_property, container, false);
        imageButton = view.findViewById(R.id.imgbtnOpenGallery);
        Button btnSave = view.findViewById(R.id.btnSave);
        Button btnReset = view.findViewById(R.id.btnReset);
        txtFileCount = view.findViewById(R.id.txtFileCount);
        initializeForm(view);

        ActivityResultLauncher<Intent> galleryActivityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @SuppressLint("ResourceAsColor")
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if(result.getResultCode() == Activity.RESULT_OK) {
                            imageDATA = result.getData();
                            if(imageDATA.getClipData() != null) {
                                txtFileCount.setText("You included ( "+imageDATA.getClipData().getItemCount()+" ) file(s)");
                                txtFileCount.setTextColor(R.color.teal_700);
                            }
                            else if(imageDATA.getData() != null) {
                                try{
                                    Uri uri = result.getData().getData();
                                    txtFileCount.setText("You included ( 1 ) file");
                                    txtFileCount.setTextColor(R.color.teal_200);
                                }
                                catch(Exception e) {
                                    Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }
                            else {
                                Toast.makeText(getContext(), "No image selected.", Toast.LENGTH_SHORT).show();
                                txtFileCount.setText("Please include a file *");
                                txtFileCount.setTextColor(R.color.red);
                            }// I think it's unused;
                        }
                        else {
                            txtFileCount.setText("Please include a file *");
                            txtFileCount.setTextColor(ContextCompat.getColor(getContext(), R.color.red));
                            Toast.makeText(getContext(), "No image selected.", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
        );

        setUpProgressBar(); // initialize a progress bar

        addVehicleController = new AddVehicleController(getContext(), addVehicleInterface, genericClassInterface, this);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    String owner = edtOwner.getText().toString();
                    String cellNo = edtCellNo.getText().toString();
                    String brand = edtBrand.getText().toString();
                    String model = edtModel.getText().toString();
                    String location = edtLocation.getText().toString();
                    double downPayment = Double.valueOf(edtDownpayment.getText().toString());
                    double installmentpaid = Double.valueOf(edtInstallmentpaid.getText().toString());
                    String installmentduration = edtInstallmentduration.getText().toString();
                    String delinquent = edtDelinquent.getText().toString();
                    String description = edtDescription.getText().toString();

                    if(imageDATA != null){
                        AddVehicleForm vForm = new AddVehicleForm(owner, cellNo, brand, model, location, downPayment, installmentpaid, installmentduration, delinquent, description);
                        dialog.setCancelable(false);
                        dialog.show();
                        addVehicleController.saveVehicleInfo(vForm, new ActiveUserSharedPref(getActivity()).activeUserID(), imageDATA, dialog, AddVehicleProperty.this);
                    }
                    else
                        Toast.makeText(getContext(), "Please provide an image.", Toast.LENGTH_LONG).show();
                }
                catch(Exception e) {
                    Toast.makeText(getContext(), "Please input a valid entry.", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnReset.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View view) {
                resetForm();
                txtFileCount.setText("Please include a file *");
                txtFileCount.setTextColor(R.color.red);
            }
        });
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkStoragePermission()) {
                    Intent i_open_gallery = new Intent(Intent.ACTION_GET_CONTENT);
                    i_open_gallery.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
                    i_open_gallery.setType("image/*");

                    galleryActivityResultLauncher.launch(i_open_gallery);
                }
                else {
                    ActivityCompat.requestPermissions((Activity) getContext(), new String[] {Manifest.permission.WRITE_EXTERNAL_STORAGE} , 101);
                }
            }
        });
        return view;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        addVehicleInterface = (AddVehicleInterface) context;
        genericClassInterface = (GenericClassInterface) context;
    }

    private void initializeForm(View view) {
        edtOwner = view.findViewById(R.id.edtOwner);edtCellNo = view.findViewById(R.id.edtCellNo);
        edtBrand = view.findViewById(R.id.edtBrand);edtModel = view.findViewById(R.id.edtModel);
        edtLocation = view.findViewById(R.id.edtLocation);edtDownpayment = view.findViewById(R.id.edtDownpayment);
        edtInstallmentpaid = view.findViewById(R.id.edtInstallmentpaid);edtInstallmentduration = view.findViewById(R.id.edtInstallmentduration);
        edtDelinquent = view.findViewById(R.id.edtDelinquent);edtDescription = view.findViewById(R.id.edtDescription);
    }
    @SuppressLint("ResourceAsColor")
    public void resetForm() {
        edtOwner.setText("");edtCellNo.setText("");edtBrand.setText("");
        edtModel.setText("");edtLocation.setText("");edtDownpayment.setText("");
        edtInstallmentpaid.setText("");edtInstallmentduration.setText("");
        edtDelinquent.setText("");edtDescription.setText("");
        txtFileCount.setText("Please include a file *");
        txtFileCount.setTextColor(R.color.red);

        imageDATA = null;
    }
    private boolean checkStoragePermission() {
        boolean result = ContextCompat.checkSelfPermission(getContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED;
        return result;
    }
    private void setUpProgressBar() {
        View progressBarView = getLayoutInflater().inflate(R.layout.progress_bar_1, null);
        TextView txtTitle = progressBarView.findViewById(R.id.txtProgress1Title);
        swipeRefreshLayout = progressBarView.findViewById(R.id.signupRefresh);

        txtTitle.setText("Uploading...");
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setView(progressBarView);
        dialog = builder.create();

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.setRefreshing(false);
                dialog.dismiss();
            }
        });
    }
}