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
import com.example.a_assmr.Views.Credentials.Account.My_Property.ViewProperty.VURCertainProperty.Model.UpdateJewelryModel;
import com.example.a_assmr.Views.Credentials.Account.My_Property.ViewProperty.VURCertainProperty.Model.UpdateServerResponse;
import com.google.android.material.textfield.TextInputEditText;

public class UpdateCertainJewelry extends AppCompatActivity {
    TextInputEditText edtOwner, edtContactno, edtJName, edtJModel, edtLocation, edtInstallmentpaid;
    TextInputEditText edtDownpayment, edtInstallmentduration, edtDelinquent, edtDescription;
    Button btnSave, btnReset;
    Bundle bundle;
    int propertyID;
    AlertDialog dialog;
    ItemViewModel viewModel;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_certain_jewelry);

        intializedProgressBar();
        initializedForms();

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    String owner = edtOwner.getText().toString();
                    String contactno = edtContactno.getText().toString();
                    String jname = edtJName.getText().toString();
                    String jmodel = edtJModel.getText().toString();
                    String location = edtLocation.getText().toString();
                    String paid = edtInstallmentpaid.getText().toString();
                    String duration = edtInstallmentduration.getText().toString();
                    String downpayment = edtDownpayment.getText().toString();
                    String delinquent = edtDelinquent.getText().toString();
                    String description = edtDescription.getText().toString();

                    String[] fields = {owner, contactno, jname, jmodel, location, paid, duration, downpayment, delinquent};
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
                        UpdateJewelryModel ujm = new UpdateJewelryModel(propertyID, owner, contactno, jname, jmodel, location, paid,
                                duration, downpayment, delinquent, description);
                        GenericClass<UpdateJewelryModel> genericClass = new GenericClass();
                        genericClass.setCertainClass(ujm);

                        GenericClass<UpdateCertainJewelry> genericClass1 = new GenericClass<>();
                        genericClass1.setCertainClass(UpdateCertainJewelry.this);

                        UpdateCertainPropController ucpc = new UpdateCertainPropController(UpdateCertainJewelry.this,
                                genericClass, dialog, genericClass1);
                        ucpc.updateCertainProperty(propertyID, "jewelry", genericClass);
                    }
                    else {
                        Toast.makeText(getBaseContext(), "Some fields are empty.", Toast.LENGTH_LONG).show();
                        dialog.dismiss();
                    }
                }
                catch(Exception e) {
                    Toast.makeText(getBaseContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });
        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edtOwner.setText(""); edtContactno.setText(""); edtJName.setText(""); edtJModel.setText("");
                edtLocation.setText(""); edtInstallmentpaid.setText(""); edtInstallmentduration.setText("");
                edtDownpayment.setText(""); edtDelinquent.setText(""); edtDescription.setText("");
            }
        });
        viewModel.getSelectedItem().observe(this, item -> {
            Object obj = item.getCertainGenericClass();
            if(obj instanceof UpdateServerResponse) {
                if(((UpdateServerResponse) obj).code == 200) {
                    Toast.makeText(this, "Successfully update.", Toast.LENGTH_SHORT).show();
                }
                else
                    Toast.makeText(this, "Update failed.", Toast.LENGTH_LONG).show();
            }
        });
    }
    public void initializedForms() {
        viewModel = new ViewModelProvider(this).get(ItemViewModel.class);

        edtOwner = findViewById(R.id.edtOwner); edtContactno = findViewById(R.id.edtContactno);
        edtJName = findViewById(R.id.edtJName); edtJModel = findViewById(R.id.edtJModel);
        edtLocation = findViewById(R.id.edtLocation); edtInstallmentpaid = findViewById(R.id.edtInstallmentpaid);
        edtDownpayment = findViewById(R.id.edtDownpayment); edtInstallmentduration  = findViewById(R.id.edtInstallmentduration);
        edtDelinquent = findViewById(R.id.edtDelinquent); edtDescription = findViewById(R.id.edtDescription);

        btnSave = findViewById(R.id.btnSave); btnReset = findViewById(R.id.btnReset);

        bundle = getIntent().getExtras();

        propertyID = bundle.getInt("propertyID");

        edtOwner.setText(bundle.getString("owner"));
        edtContactno.setText(bundle.getString("contactno"));
        edtJName.setText(bundle.getString("jewelry_name"));
        edtJModel.setText(bundle.getString("jewelry_model"));
        edtLocation.setText(bundle.getString("location"));
        edtDownpayment.setText(bundle.getString("downpayment"));
        edtInstallmentpaid.setText(bundle.getString("paid"));
        edtInstallmentduration.setText(bundle.getString("duration"));
        edtDelinquent.setText(bundle.getString("delinquent"));
        edtDescription.setText(bundle.getString("description"));
    }
    public void intializedProgressBar() {
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
            }
        });
        dialog = builder.create();
    }
}
