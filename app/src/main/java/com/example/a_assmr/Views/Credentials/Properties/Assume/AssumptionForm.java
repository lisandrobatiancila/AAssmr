package com.example.a_assmr.Views.Credentials.Properties.Assume;

import android.app.AlertDialog;
import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a_assmr.CommonDir.GenericClass;
import com.example.a_assmr.R;
import com.example.a_assmr.Views.Credentials.Properties.Assume.Controller.AssumptionController;
import com.example.a_assmr.Views.Credentials.Properties.Assume.Interface.AssumptionInterface;
import com.example.a_assmr.Views.Credentials.Properties.Assume.Model.AssumerInfoModel;
import com.example.a_assmr.Views.Credentials.Properties.Assume.Model.AssumptionFormModel;
import com.example.a_assmr.Views.Credentials.Properties.Assume.Model.AssumptionParentModel;
import com.example.a_assmr.Views.Credentials.Properties.Assume.Model.AssumptionServerResponse;
import com.example.a_assmr.Views.Credentials.Signup.Model.Cities;
import com.example.a_assmr.Views.Credentials.Signup.Model.Provinces;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AssumptionForm implements AssumptionInterface{
    Context context;
    AlertDialog dialog, assumptionDialog;
    TextInputEditText edtFirstname, edtMiddlename, edtLastname, edtContactno, edtAddress, edtWork, edtSalary;
    Spinner spinner_city, spinner_province, spinner_barangay;
    HashMap<String, List<Provinces>> hashedCities = new HashMap<>();
    HashMap<String, List<String>> hashedProvinces = new HashMap<>();
    int is_first_time_province, is_first_time_city = 0;
    int userID, propertyID;
    String city, province, barangay;
    public AssumptionForm(Context context, int userID, int propertyID) {
        this.context = context;
        this.userID = userID;
        this.propertyID = propertyID;
    }

    public void getAssumerInformation() {
        AssumptionController assumptionController = new AssumptionController(context, this);
        assumptionController.getAssummerInformation(userID);
    }
    public void setDialogInstance(AlertDialog dialog) {
        this.dialog = dialog;
    }
    public View AssumptionInitForm(Context context) {
        View view = View.inflate(context, R.layout.assumption_form, null);

        edtFirstname = view.findViewById(R.id.edtFirstname); edtMiddlename = view.findViewById(R.id.edtMiddlename);
        edtLastname = view.findViewById(R.id.edtLastname); edtContactno = view.findViewById(R.id.edtContactno);
        edtAddress = view.findViewById(R.id.edtAddress); edtWork = view.findViewById(R.id.edtWork); edtSalary = view.findViewById(R.id.edtSalary);

        spinner_city = view.findViewById(R.id.spinnerCity);
        spinner_province = view.findViewById(R.id.spinnerProvince);
        spinner_barangay = view.findViewById(R.id.spinnerBarangay);

        Button btnProceed = view.findViewById(R.id.btnProceed);
        Button btnClose = view.findViewById(R.id.btnClose);

        btnProceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    String firstname = edtFirstname.getText().toString();
                    String middlename = edtMiddlename.getText().toString();
                    String lastname = edtLastname.getText().toString();
                    String contactno = edtContactno.getText().toString();
                    String work = edtWork.getText().toString();
                    String salary = edtSalary.getText().toString();
                    String address = edtAddress.getText().toString();

                    String[] fields = {firstname, middlename, lastname, contactno, work, salary, address};
                    int fieldsLen = fields.length;
                    boolean is_passed = true;
                    for(int i = 0; i < fieldsLen; i++) {
                        if(fields[i].isEmpty()) {
                            is_passed = false;
                            break;
                        }
                    } // end of for loop validation

                    if(is_passed) {
                        AssumptionFormModel assumptionFormModel = new AssumptionFormModel(userID, propertyID, firstname, middlename, lastname,
                                contactno, address, work, salary);

                        AssumptionController assumptionController = new AssumptionController(context, AssumptionForm.this);
                        assumptionController.submitAssumptionForm(assumptionFormModel);
                    }
                    else
                        Toast.makeText(context, "Some fields are empty.", Toast.LENGTH_LONG).show();

                }
                catch(Exception error) {
                    Toast.makeText(context, "Something went wrong", Toast.LENGTH_LONG).show();
                }
            }
        });

        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        spinner_city.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(++is_first_time_city > 2) {
                    Object obj = adapterView.getSelectedItem();
                    city = obj.toString();
                    List<String> provinceList = new ArrayList<>();
                    hashedProvinces.clear();

                    for(Provinces p: hashedCities.get(obj)) {
                        provinceList.add(p.name);
                        hashedProvinces.put(p.name, p.barangay);
                    }
                    edtAddress.setText(barangay+", "+province+", "+city);

                    spinner_province.setAdapter(new ArrayAdapter<>(context, android.R.layout.simple_list_item_1, provinceList));
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        }); // spinner_city listener

        spinner_province.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(++is_first_time_province > 2) {
                    Object obj = adapterView.getSelectedItem();
                    List<String> barangayList = new ArrayList<>();
                    province = obj.toString();

                    for(String barangay: hashedProvinces.get(obj)) {
                        barangayList.add(barangay);
                    }
                    barangay = barangayList.get(0);
                    edtAddress.setText(barangay+", "+province+", "+city);

                    spinner_barangay.setAdapter(new ArrayAdapter<>(context, android.R.layout.simple_list_item_1, barangayList));
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        return view;
    }

    @Override
    public void AssumptionResponses(GenericClass genericClass) {
        Object object = genericClass.getCertainClass();
        if(object instanceof AssumptionParentModel) {
            List<AssumerInfoModel> assumerInfoModels = ((AssumptionParentModel) object).getAssumerInfoModels();
            List<Cities> cities = ((AssumptionParentModel) object).getAddressInfoModels();

            edtFirstname.setText(assumerInfoModels.get(0).getUserFname());
            edtMiddlename.setText(assumerInfoModels.get(0).getUserMname());
            edtLastname.setText(assumerInfoModels.get(0).getUserLname());
            edtContactno.setText(assumerInfoModels.get(0).getUserContactno());

            String[] address = assumerInfoModels.get(0).getUserAddress().split(",");

            city = address[2];
            province = address[1];
            barangay = address[0];

            edtAddress.setText(barangay+", "+province+", "+city);

            List<String> cityLists = new ArrayList<>();
            int cityLen = cities.size();

            for(int city_index = 0; city_index < cityLen; city_index++) {
                cityLists.add(cities.get(city_index).city);
                hashedCities.put(cities.get(city_index).city, cities.get(city_index).provinces);
            }
            ArrayAdapter city_adapter = new ArrayAdapter(context, android.R.layout.simple_list_item_1, cityLists);
            spinner_city.setAdapter(city_adapter);
            // INITIALIZED THE CITY ADDRESS
            List<String> provinceList = new ArrayList<>();
            for(Provinces p: cities.get(0).provinces) {
                provinceList.add(p.name);
                hashedProvinces.put(p.name, p.barangay);
            }
            ArrayAdapter province_adapter = new ArrayAdapter(context, android.R.layout.simple_list_item_1, provinceList);
            spinner_province.setAdapter(province_adapter);
            // INITIALIZED THE PROVINCE ADDRESS
            List<String> barangayList =new ArrayList<>();
            for(String barangay: hashedProvinces.get(province.trim())) {
                barangayList.add(barangay);
            }
            ArrayAdapter barangay_adapter = new ArrayAdapter(context, android.R.layout.simple_list_item_1, barangayList);
            spinner_barangay.setAdapter(barangay_adapter);
            // INITIALIZED THE BARANGAY ADDRESS

            //WE CUSTOMIZED THE SPINNER VALUE; AND SET / PUT / INIT THE ASSUMER ADDRESS INFO

            int cityPos = 0, provincePos = 0, barangayPos = 0;

            cityLen = cityLists.size();
            int provinceLen = provinceList.size(), barangayLen = barangayList.size();

            for(int i = 0; i < cityLen; i++) {
                if(cityLists.get(i).trim().equals(city.trim()))
                    break;
                else
                    cityPos+=1;
            }
            spinner_city.setSelection(cityPos);
            // set the spinner_city default value
            for(int i = 0; i < provinceLen; i++) {
                if(provinceList.get(i).trim().equals(province.trim())) {
                    provincePos = i;
                    break;
                }
            }
            spinner_province.setSelection(provincePos);
            // set the spinner_province default value
            for(int i = 0; i < barangayLen; i++) {
                if(barangayList.get(i).trim().equals(barangay.trim())) {
                    barangayPos = i;
                    break;
                }
            }
            spinner_barangay.setSelection(barangayPos);
            // set the spinner_barangay default value
        }
        else if(object instanceof AssumptionServerResponse) {
            if(((AssumptionServerResponse) object).getCode() == 200) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                View view = View.inflate(context, R.layout.successfull_responses, null);

                TextView txtMessage = view.findViewById(R.id.txtMessage);
                txtMessage.setText(((AssumptionServerResponse) object).getMessage());

                Button btnOk = view.findViewById(R.id.btnOk);
                btnOk.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        assumptionDialog.dismiss();
                    }
                });

                builder.setView(view).setCancelable(false);
                assumptionDialog = builder.create();
                assumptionDialog.show();
            }
            else {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                View view = View.inflate(context, R.layout.fail_responses, null);

                TextView txtMessage = view.findViewById(R.id.txtMessage);
                txtMessage.setText(((AssumptionServerResponse) object).getMessage());

                Button btnOk = view.findViewById(R.id.btnOk);
                btnOk.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        assumptionDialog.dismiss();
                    }
                });
                builder.setView(view).setCancelable(false);
                assumptionDialog = builder.create();
                assumptionDialog.show();
            }
        }
    }
}
