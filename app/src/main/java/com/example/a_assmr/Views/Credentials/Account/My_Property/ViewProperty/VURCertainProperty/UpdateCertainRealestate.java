package com.example.a_assmr.Views.Credentials.Account.My_Property.ViewProperty.VURCertainProperty;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.a_assmr.CommonDir.GenericClass;
import com.example.a_assmr.R;
import com.example.a_assmr.Views.Credentials.Account.My_Property.ViewProperty.ItemViewModel;
import com.example.a_assmr.Views.Credentials.Account.My_Property.ViewProperty.VURCertainProperty.Controller.UpdateCertainPropController;
import com.example.a_assmr.Views.Credentials.Account.My_Property.ViewProperty.VURCertainProperty.Model.UpdateRealestateModel;
import com.example.a_assmr.Views.Credentials.Account.My_Property.ViewProperty.VURCertainProperty.Model.UpdateServerResponse;
import com.google.android.material.textfield.TextInputEditText;

public class UpdateCertainRealestate extends AppCompatActivity {
    TextInputEditText edtOwner, edtRealType, edtConactno, edtLocation, edtDownpayment;
    TextInputEditText edtInstallmentpaid, edtInstallmentduration, edtDelinquent, edtDescription;
    Button btnSave, btnReset;
    int propertyID;
    AlertDialog dialog;
    ItemViewModel itemViewModel;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_certain_realestate);

        initialzedProgressBar();
        initializedFields();

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    String owner = edtOwner.getText().toString();
                    String contactno = edtConactno.getText().toString();
                    String location = edtLocation.getText().toString();
                    String downpayment = edtDownpayment.getText().toString();
                    String paid = edtInstallmentpaid.getText().toString();
                    String duration = edtInstallmentduration.getText().toString();
                    String delinquent = edtDelinquent.getText().toString();
                    String description = edtDescription.getText().toString();

                    String[] fields = {owner, contactno, location, downpayment, paid, duration, delinquent};
                    int fieldsLen = fields.length;
                    boolean is_passed = true;

                    for(int i = 0; i < fieldsLen; i++) {
                        if(fields[i].isEmpty()) {
                            is_passed = false;
                            break;
                        }
                    } // end of for loop

                    if(is_passed) {
                        dialog.show();
                        UpdateRealestateModel urm = new UpdateRealestateModel(propertyID, owner, contactno, location, downpayment, paid, duration,
                                delinquent, description);
                        GenericClass<UpdateRealestateModel> genericClass = new GenericClass<>();
                        genericClass.setCertainClass(urm);

                        GenericClass<UpdateCertainRealestate> genericClass1 = new GenericClass<>();
                        genericClass1.setCertainClass(UpdateCertainRealestate.this);

                        UpdateCertainPropController ucpc = new UpdateCertainPropController(UpdateCertainRealestate.this, genericClass,
                                dialog, genericClass1);
                        ucpc.updateCertainProperty(propertyID, "realestate", genericClass);

                        itemViewModel.getSelectedItem().observe(UpdateCertainRealestate.this, item -> {
                            Object obj = item.getCertainGenericClass();
                            if(obj instanceof UpdateServerResponse) {
                                if(((UpdateServerResponse) obj).code == 200) {
                                    Toast.makeText(getBaseContext(), "Successfully updated.", Toast.LENGTH_SHORT).show();
                                }
                                else
                                    Toast.makeText(getBaseContext(), "Update has failed.", Toast.LENGTH_LONG).show();
                            }
                        });
                    }
                    else
                        Toast.makeText(getApplicationContext(), "Some fields are empty.", Toast.LENGTH_SHORT).show();
                }
                catch(Exception error) {
                    Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edtOwner.setText(""); edtRealType.setText(""); edtConactno.setText(""); edtLocation.setText("");
                edtDownpayment.setText(""); edtInstallmentpaid.setText(""); edtInstallmentduration.setText("");
                edtDelinquent.setText(""); edtDescription.setText("");
            }
        });
    }
    public void initializedFields() {
        edtOwner = findViewById(R.id.edtOwner); edtRealType = findViewById(R.id.edtRealType); edtConactno = findViewById(R.id.edtContactno);
        edtLocation = findViewById(R.id.edtLocation); edtDownpayment = findViewById(R.id.edtDownpayment);
        edtInstallmentpaid = findViewById(R.id.edtInstallmentpaid); edtInstallmentduration = findViewById(R.id.edtInstallmentduration);
        edtDelinquent = findViewById(R.id.edtDelinquent); edtDescription = findViewById(R.id.edtDescription);

        btnSave = findViewById(R.id.btnSave);
        btnReset = findViewById(R.id.btnReset);

        Bundle bundle = getIntent().getExtras();
        itemViewModel = new ViewModelProvider(this).get(ItemViewModel.class);

        propertyID = bundle.getInt("propertyID");

        edtOwner.setText(bundle.getString("owner"));
        edtRealType.setText(bundle.getString("type"));
        edtConactno.setText(bundle.getString("contactno"));
        edtLocation.setText(bundle.getString("location"));
        edtDownpayment.setText(String.valueOf(bundle.getDouble("downpayment")));
        edtInstallmentpaid.setText(String.valueOf(bundle.getDouble("paid")));
        edtInstallmentduration.setText(bundle.getString("duration"));
        edtDelinquent.setText(bundle.getString("delinquent"));
        edtDescription.setText(bundle.getString("description"));
    }
    public void initialzedProgressBar() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view = getLayoutInflater().inflate(R.layout.progress_bar_1, null);
        builder.setView(view);
        builder.setCancelable(false);

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

        dialog = builder.create();
    }
}
