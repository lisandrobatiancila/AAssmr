package com.example.a_assmr.Views.Credentials.Account.My_Property.ViewProperty.VURCertainProperty;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.a_assmr.CommonDir.GenericClass;
import com.example.a_assmr.R;
import com.example.a_assmr.Views.Credentials.Account.My_Property.ViewProperty.VURCertainProperty.Controller.UpdateCertainPropController;
import com.example.a_assmr.Views.Credentials.Account.My_Property.ViewProperty.VURCertainProperty.Model.UpdateServerResponse;
import com.example.a_assmr.Views.Credentials.Account.My_Property.ViewProperty.VURCertainProperty.Model.UpdateVehicleModel;
import com.example.a_assmr.Views.Credentials.Account.My_Property.ViewProperty.ItemViewModel;
import com.google.android.material.textfield.TextInputEditText;

public class UpdateCertainVehicle extends AppCompatActivity {
    TextInputEditText edtOwner, edtContactno, edtBrand, edtModel, edtLocation, edtInstallmentpaid;
    TextInputEditText edtDownpayment, edtInstallmentduration, edtDelinquent, edtDescription;
    Button btnSave, btnReset;
    Bundle bundle;
    int propertyID;
    AlertDialog dialog;
    ItemViewModel viewModel;
    @SuppressLint("ServiceCast")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_certain_vehicle);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        initializedProgressBar();

        bundle = getIntent().getExtras();

        initializedForms();
        classGenericActions();
    }
    public void initializedForms() {
        edtOwner = findViewById(R.id.edtOwner);
        edtContactno = findViewById(R.id.edtContactno);
        edtBrand = findViewById(R.id.edtBrand);
        edtModel = findViewById(R.id.edtModel);
        edtLocation = findViewById(R.id.edtLocation);
        edtDownpayment = findViewById(R.id.edtDownpayment);
        edtInstallmentpaid = findViewById(R.id.edtInstallmentpaid);
        edtInstallmentduration = findViewById(R.id.edtInstallmentduration);
        edtDelinquent = findViewById(R.id.edtDelinquent);
        edtDescription = findViewById(R.id.edtDescription);

        btnSave = findViewById(R.id.btnSave);
        btnReset = findViewById(R.id.btnReset);

        viewModel = new ViewModelProvider(UpdateCertainVehicle.this).get(ItemViewModel.class);

        propertyID = bundle.getInt("propertyID");
        edtOwner.setText(bundle.getString("owner"));
        edtContactno.setText(bundle.getString("contactno"));
        edtBrand.setText(bundle.getString("brand"));
        edtModel.setText(bundle.getString("model"));
        edtLocation.setText(bundle.getString("location"));
        edtDownpayment.setText(bundle.getString("downpayment"));
        edtInstallmentpaid.setText(bundle.getString("installmentpaid"));
        edtInstallmentduration.setText(bundle.getString("installmentduration"));
        edtDelinquent.setText(bundle.getString("delinquent"));
        edtDescription.setText(bundle.getString("description"));
    }
    public void classGenericActions() {
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.show();

                String owner = edtOwner.getText().toString();
                String contactno = edtContactno.getText().toString();
                String brand = edtBrand.getText().toString();
                String model = edtModel.getText().toString();
                String location = edtLocation.getText().toString();
                String downpayment = edtDownpayment.getText().toString();
                String paid = edtInstallmentpaid.getText().toString();
                String duration = edtInstallmentduration.getText().toString();
                String delinquent = edtDelinquent.getText().toString();
                String description = edtDescription.getText().toString();

                try{
                    String[] fields = {owner, contactno, brand, model, location, paid, duration, delinquent};
                    int fieldLen = fields.length;
                    boolean should_fail = false;

                    for(int i = 0; i < fieldLen; i++) {
                        if(!fields[i].isEmpty())
                            continue;
                        else {
                            should_fail = true;
                            break;
                        }
                    } // end of for
                    if(!should_fail) {
                        UpdateVehicleModel ucvm = new UpdateVehicleModel(propertyID, owner, contactno, brand, model, location, downpayment,
                                paid, duration, delinquent, description); // update certain vehicle model

                        GenericClass<UpdateVehicleModel> genericClass = new GenericClass<>();
                        genericClass.setCertainClass(ucvm);

                        UpdateCertainPropController ucpc = new UpdateCertainPropController(getApplicationContext(), genericClass, dialog,
                                UpdateCertainVehicle.this); // controller
                        ucpc.updateCertainProperty(propertyID, "vehicle", genericClass);
                    }
                    else {
                        Toast.makeText(getApplicationContext(), "Somefields are empty.", Toast.LENGTH_LONG).show();
                        dialog.dismiss();
                    }
                }
                catch(Exception e) {
                    Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });
        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edtOwner.setText(""); edtContactno.setText(""); edtBrand.setText(""); edtModel.setText("");
                edtLocation.setText(""); edtInstallmentpaid.setText(""); edtInstallmentduration.setText("");
                edtDelinquent.setText(""); edtDelinquent.setText(""); edtDescription.setText(""); edtDownpayment.setText("");
            }
        });

        viewModel.getSelectedItem().observe(this, item -> {
            Object obj = item.getCertainGenericClass();
            if(obj instanceof UpdateServerResponse) {
                if(((UpdateServerResponse) obj).code != 500) {
                    Toast.makeText(getApplicationContext(), "Successfully updated.", Toast.LENGTH_SHORT).show();
                }
                else
                    Toast.makeText(getApplicationContext(), "Update has failed", Toast.LENGTH_LONG).show();
            }
        });
    }
    private void initializedProgressBar() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        View view = getLayoutInflater().inflate(R.layout.progress_bar_1, null);
        builder.setView(view);
        dialog = builder.create();

        TextView txtTitle = view.findViewById(R.id.txtProgress1Title);
        SwipeRefreshLayout swipeRefreshLayout = view.findViewById(R.id.signupRefresh);

        txtTitle.setText("Updating information.");

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.setRefreshing(false);
                dialog.dismiss();
            }
        });
    }
}
