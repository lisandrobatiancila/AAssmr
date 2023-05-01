package com.example.a_assmr.Views.Credentials.Account.My_Property.Settings;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.a_assmr.CommonDir.ActiveUserSharedPref;
import com.example.a_assmr.R;
import com.example.a_assmr.Views.Credentials.Account.My_Property.Settings.Controller.SettingsController;
import com.example.a_assmr.Views.Credentials.Account.My_Property.Settings.Model.SettingsAccountInformationModel;
import com.example.a_assmr.Views.Credentials.Account.My_Property.Settings.Model.SettingsModel;
import com.example.a_assmr.Views.Credentials.Account.My_Property.Settings.Model.SettingsPersonalInformationModel;
import com.example.a_assmr.Views.Credentials.Account.My_Property.ViewProperty.ItemViewModel;
import com.example.a_assmr.Views.Credentials.Signup.Model.Cities;
import com.example.a_assmr.Views.Credentials.Signup.Model.Provinces;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SettingsFragment extends Fragment {
    EditText edtFirstname, edtMiddlename, edtLastname, edtContactno, edtAddress;
    Spinner spinnerCity, spinnerProvince, spinnerBarangay;
    EditText edtEmial, edtPassword;
    Button btnSaveAI, btnSavePI;
    Context context;
    String city = "", province = "", barangay = "";
    SettingsController settingsController;
    ActiveUserSharedPref activeUserSharedPref;
    ItemViewModel itemViewModel;
    Map<String, List<Provinces>> hashedCities = new HashMap<>();
    Map<String, List<String>> hashedProvinces = new HashMap<>();
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.account_settings, container, false);

        initForm(view);
        spinnerCity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String city = adapterView.getSelectedItem().toString();
                List<String> provincesList = new ArrayList<>();
                for(Provinces p: hashedCities.get(city)) {
                    provincesList.add(p.name);
                    hashedProvinces.put(p.name, p.barangay);
                }
                ArrayAdapter provinceAdapter = new ArrayAdapter(requireActivity(), android.R.layout.simple_list_item_1, provincesList);
                spinnerProvince.setAdapter(provinceAdapter);

                String[] address = edtAddress.getText().toString().split(",");
                address[2] = city;
//                address[1] = hashedCities.get(city).get(0).name.toString();
                System.out.println(hashedCities.get(city).get(0).name);
                String display = address[0].trim()+", "+address[1].trim()+", "+address[2].trim();

                edtAddress.setText(display);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        }); // citySpinner
        spinnerProvince.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String province = adapterView.getSelectedItem().toString();
                List<String> barangayList = new ArrayList<>();
                for(String barangay: hashedProvinces.get(province)) {
                    barangayList.add(barangay);
                }
                ArrayAdapter barangayAdapter = new ArrayAdapter(requireActivity(), android.R.layout.simple_list_item_1, barangayList);
                spinnerBarangay.setAdapter(barangayAdapter);

                String[] address = edtAddress.getText().toString().split(",");
                address[1] = province;
                String display = address[0].trim()+", "+address[1].trim()+", "+address[2].trim();

                edtAddress.setText(display);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        }); // provinceSpinner
        spinnerBarangay.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String barangay = adapterView.getSelectedItem().toString();
                String[] address = edtAddress.getText().toString().split(",");
                address[0] = barangay;
                String display = address[0].trim()+", "+address[1].trim()+", "+address[2].trim();

                edtAddress.setText(display);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        }); // barangaySpinner

        btnSavePI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String firstname = edtFirstname.getText().toString();
                String middlename = edtMiddlename.getText().toString();
                String lastname = edtLastname.getText().toString();
                String contactno = edtContactno.getText().toString();
                String address = edtAddress.getText().toString();
                String[] fields = {firstname, middlename, lastname, contactno};
                int fieldsLen = fields.length;
                boolean isAccepted = false;

                for(int i = 0; i < fieldsLen; i++) {
                    if(fields[i].isEmpty())
                        break;
                    else
                        isAccepted = true;
                } // end for-loop

                if(isAccepted) {
                    SettingsPersonalInformationModel SPI = new SettingsPersonalInformationModel (new ActiveUserSharedPref(context).activeUserID(), firstname, middlename, lastname, contactno, address);
                    settingsController.updatePersonalInformation(SPI);
                }
                else
                    Toast.makeText(context, "Some fields are empty!", Toast.LENGTH_LONG).show();
            }
        });
        btnSaveAI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = edtEmial.getText().toString();
                String password = edtPassword.getText().toString();

                SettingsAccountInformationModel accountInformationModel = new SettingsAccountInformationModel(new ActiveUserSharedPref(context).activeUserID(),
                        email, password);
                settingsController.updateAccountInformation(accountInformationModel);
            }
        });

        return view;
    }

    private void initForm(View v) {
        activeUserSharedPref = new ActiveUserSharedPref(requireActivity());

        itemViewModel = new ViewModelProvider(requireActivity()).get(ItemViewModel.class);

        edtFirstname = v.findViewById(R.id.edtFirstname); edtMiddlename = v.findViewById(R.id.edtMiddlename);
        edtLastname = v.findViewById(R.id.edtLastname); edtContactno = v.findViewById(R.id.edtContactno);
        edtAddress = v.findViewById(R.id.edtLocation);
        spinnerCity = v.findViewById(R.id.spinnerCity); spinnerProvince = v.findViewById(R.id.spinnerProvince); spinnerBarangay = v.findViewById(R.id.spinnerBarangay);

        edtEmial = v.findViewById(R.id.edtEmail); edtPassword = v.findViewById(R.id.edtPassword);

        btnSavePI = v.findViewById(R.id.btnSavePI); btnSaveAI = v.findViewById(R.id.btnSaveAI);

        settingsController = new SettingsController(requireActivity(), requireActivity());
        settingsController.getSettingsInformation(activeUserSharedPref.activeUserID());

        itemViewModel.getSelectedItem().observe(requireActivity(), item -> {
            Object obj = item.getCertainGenericClass();
            if(obj instanceof SettingsModel) {
                edtFirstname.setText(((SettingsModel) obj).getUserSettingsInformation().get(0).getUserFname());
                edtMiddlename.setText(((SettingsModel) obj).getUserSettingsInformation().get(0).getUserMname());
                edtLastname.setText(((SettingsModel) obj).getUserSettingsInformation().get(0).getUserLname());
                edtContactno.setText(((SettingsModel) obj).getUserSettingsInformation().get(0).getUserContactno());
                edtAddress.setText(((SettingsModel) obj).getUserSettingsInformation().get(0).getUserAddress());

                String[] address = ((SettingsModel) obj).getUserSettingsInformation().get(0).getUserAddress().split(",");
                System.out.println(((SettingsModel) obj).getUserSettingsInformation().get(0).getUserAddress());
                String strCity = address[2];
                String strProvince = address[1];
                String strBarangay = address[0];

                List<Cities> addressDBLists = ((SettingsModel) obj).getAddress();
                int addressLen = addressDBLists.size();

                List<String> cityLists = new ArrayList<>();
                int cityPos = 0;
                boolean posFound = false;

                for(Cities cities : addressDBLists) {
                    if(strCity.trim().equals(cities.city.trim()))
                        posFound = true;
                    else if(!posFound)
                        cityPos+=1;

                    hashedCities.put(cities.city.trim(), cities.provinces);
                    cityLists.add(cities.city.trim());
                }

                List<String> provinceLists = new ArrayList<>();
                int provincePos = -1;
                posFound = false;
                for(Provinces province: addressDBLists.get(0).provinces) {
                    if(strProvince.trim().equals(province.name.trim()))
                        posFound = true;
                    else if(!posFound)
                        provincePos+=1;

                    provinceLists.add(province.name.trim());
                    hashedProvinces.put(province.name.trim(), province.barangay);
                }

                ArrayAdapter cityAdapter = new ArrayAdapter(requireActivity(), android.R.layout.simple_list_item_1, cityLists);
                spinnerCity.setAdapter(cityAdapter);

                ArrayAdapter provinceAdapter = new ArrayAdapter(requireActivity(), android.R.layout.simple_list_item_1, provinceLists);
                spinnerProvince.setAdapter(provinceAdapter);

                spinnerCity.setSelection(cityPos);
                spinnerProvince.setSelection(provincePos);
                // Done Setting Address

                edtEmial.setText(((SettingsModel) obj).getUserSettingsInformation().get(0).getUserEmail());
                edtPassword.setText(((SettingsModel) obj).getUserSettingsInformation().get(0).getAccount_key());
            }
        });
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;
    }
}
