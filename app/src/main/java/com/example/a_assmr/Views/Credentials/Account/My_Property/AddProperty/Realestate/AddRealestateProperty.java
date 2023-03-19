package com.example.a_assmr.Views.Credentials.Account.My_Property.AddProperty.Realestate;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
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
import com.example.a_assmr.Views.Credentials.Account.My_Property.AddProperty.Realestate.Controller.AddRealestateController;
import com.example.a_assmr.Views.Credentials.Account.My_Property.AddProperty.Realestate.Interface.AddRealesateInterface;
import com.example.a_assmr.Views.Credentials.Account.My_Property.AddProperty.Realestate.Model.AddRealestateForm;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;

public class AddRealestateProperty extends Fragment {
    TextInputEditText edtOwner, edtCellNo, edtLocation, edtDownpayment, edtInstallmentpaid, edtInstallmentduration;
    TextInputEditText edtDelinquent, edtDescription;
    TextView txtFileCount;
    Spinner spinnerRealestateType;
    Button btnSave, btnReset;
    ImageButton imgbtnOpenGallery;
    String realestateType = "house";
    String[] realestateLists = {"House", "House and Lot", "Lot"};
    Intent imageData = null;
    AddRealestateController realestateController;
    AlertDialog dialog;
    AddRealesateInterface addRealesateInterface;
    GenericClassInterface genericClassInterface;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.account_add_realestate_property, container, false);
        initializeForm(view);
        setUpProgressBar();

        realestateController = new AddRealestateController(getContext(), addRealesateInterface, genericClassInterface, this);

        ActivityResultLauncher<Intent> realestateLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @SuppressLint("ResourceAsColor")
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        imageData = result.getData();
                        if(imageData.getClipData() != null) {
                            txtFileCount.setText("You included ( "+imageData.getClipData().getItemCount()+" ) file(s)");
                            txtFileCount.setTextColor(R.color.teal_700);
                        }
                        else if(imageData.getData() != null) {
                            txtFileCount.setText("You included ( 1 ) file");
                            txtFileCount.setTextColor(R.color.teal_700);
                        }
                        else
                            Toast.makeText(getContext(), "Please select an image", Toast.LENGTH_LONG).show();
                    }
                }
        );
        spinnerRealestateType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                realestateType = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        imgbtnOpenGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkPermission()) {
                    Intent i_open_gallery = new Intent(Intent.ACTION_GET_CONTENT);
                    i_open_gallery.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
                    i_open_gallery.setType("image/*");

                    realestateLauncher.launch(i_open_gallery);
                }
                else
                    ActivityCompat.requestPermissions(getActivity(), new String[] {Manifest.permission.WRITE_EXTERNAL_STORAGE}, 101);
            }
        });
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    String owner = edtOwner.getText().toString();
                    String mobileNo = edtCellNo.getText().toString();
                    String location = edtLocation.getText().toString();
                    Double downpayment = Double.valueOf(edtDownpayment.getText().toString());
                    Double installmentpaid = Double.valueOf(edtInstallmentpaid.getText().toString());
                    String installmentduration = edtInstallmentduration.getText().toString();
                    String delinquent = edtDelinquent.getText().toString();
                    String description = edtDescription.getText().toString();
                    AddRealestateForm realForm = new AddRealestateForm(owner, mobileNo, location, installmentduration, downpayment, installmentpaid, delinquent, realestateType, description);
                    ActiveUserSharedPref userRef = new ActiveUserSharedPref(getContext());

                    if(imageData != null) {
                        dialog.setCancelable(false);
                        dialog.show();

                        realestateController.saveRealestateInfo(realForm, userRef.activeUserID(), dialog, imageData);
                    }
                    else
                        Toast.makeText(getContext(), "Please provide an image.", Toast.LENGTH_LONG).show();
                }
                catch(Exception e) {
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
        return view;
    }
    private void initializeForm(View view) {
        edtOwner = view.findViewById(R.id.edtOwner);edtCellNo = view.findViewById(R.id.edtCellNo);edtLocation = view.findViewById(R.id.edtLocation);
        edtDownpayment = view.findViewById(R.id.edtDownpayment);edtInstallmentpaid = view.findViewById(R.id.edtInstallmentpaid);
        edtInstallmentduration = view.findViewById(R.id.edtInstallmentduration);
        edtDelinquent = view.findViewById(R.id.edtDelinquent);edtDescription = view.findViewById(R.id.edtDescription);
        spinnerRealestateType = view.findViewById(R.id.spinnerRealestateType);
        btnSave = view.findViewById(R.id.btnSave);
        btnReset = view.findViewById(R.id.btnReset);
        imgbtnOpenGallery = view.findViewById(R.id.imgbtnOpenGallery);

        txtFileCount = view.findViewById(R.id.txtFileCount);

        ArrayAdapter<ArrayList<String>> adapter = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_item, realestateLists);
        spinnerRealestateType.setAdapter(adapter);
    }
    @SuppressLint("ResourceAsColor")
    public void resetForm() {
        edtOwner.setText("");edtCellNo.setText("");edtLocation.setText("");edtDownpayment.setText("");edtInstallmentpaid.setText("");
        edtInstallmentduration.setText("");edtDelinquent.setText("");edtDescription.setText("");
        txtFileCount.setText("Please include a file *");
        txtFileCount.setTextColor(R.color.red);

        imageData = null;
    }
    private boolean checkPermission() {
        boolean result = ContextCompat.checkSelfPermission(getContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED;
        return result;

    }
    @SuppressLint("MissingInflatedId")
    private void setUpProgressBar() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        View view = getLayoutInflater().inflate(R.layout.progress_bar_1, null);
        TextView txtTitle = view.findViewById(R.id.txtProgress1Title);
        SwipeRefreshLayout refreshLayout = view.findViewById(R.id.signupRefresh);

        txtTitle.setText("Uploading...");
        builder.setView(view);
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
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        addRealesateInterface = (AddRealesateInterface) context;
        genericClassInterface = (GenericClassInterface) context;
    }
}
