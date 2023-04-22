package com.example.a_assmr.Views.Credentials.Properties.View;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.a_assmr.Common;
import com.example.a_assmr.CommonDir.ActiveUserSharedPref;
import com.example.a_assmr.CommonDir.GenericClass;
import com.example.a_assmr.R;
import com.example.a_assmr.Views.Credentials.Properties.Assume.AssumptionForm;
import com.example.a_assmr.Views.Credentials.Properties.View.Controller.PropertyViewCertainController;
import com.example.a_assmr.Views.Credentials.Properties.View.Interface.PVCInterface;
import com.example.a_assmr.Views.Credentials.Properties.View.Model.PVCVehicleModel;

import java.util.ArrayList;
import java.util.List;

public class PropertyViewCertainVehicle extends AppCompatActivity implements PVCInterface {
    SwipeRefreshLayout swipeRefreshLayout;
    ImageSlider imageSlider;
    TextView txtStatus, txtOwner, txtContactno, txtBrand, txtModel, txtLocation, txtDownpayment;
    TextView txtInstallmentpaid, txtInstallmentduration, txtDelinquent, txtDescription;
    Button btnAssume, btnBack;
    Bundle bundle;
    int propertyID;
    Common common;
    AlertDialog dialog;
    PropertyViewCertainVehicle self;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.property_view_certain_vehicle);

        initForm();
        initControllerAPI();

        btnAssume.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ActiveUserSharedPref sharedPref = new ActiveUserSharedPref(self);

                AssumptionForm assumptionForm = new AssumptionForm(self, sharedPref.activeUserID(),
                        propertyID);
                AlertDialog.Builder builder = new AlertDialog.Builder(self);

                View assumption_form_modal = assumptionForm.AssumptionInitForm(self);
                builder.setView(assumption_form_modal);

                builder.setCancelable(false);

                dialog = builder.create();
                assumptionForm.setDialogInstance(dialog);
                assumptionForm.getAssumerInformation(); // userID and propertyID was set already

                dialog.show();
            }
        }); // assume a property "open a modal"

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PropertyViewCertainVehicle.super.onBackPressed();
            }
        });

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.setRefreshing(false);

                initControllerAPI();
            }
        });
    }
    private void initForm() {
        self = PropertyViewCertainVehicle.this;
        bundle = getIntent().getExtras();
        propertyID = bundle.getInt("property_id");

        imageSlider = findViewById(R.id.imgSlider);
        swipeRefreshLayout = findViewById(R.id.swipeRefreshLayout);
        txtStatus = findViewById(R.id.txtStatus); txtOwner = findViewById(R.id.txtOwner); txtContactno = findViewById(R.id.txtContactno);
        txtBrand = findViewById(R.id.txtBrand); txtModel = findViewById(R.id.txtModel); txtLocation = findViewById(R.id.txtLocation);
        txtInstallmentpaid = findViewById(R.id.txtInstallmentpaid); txtInstallmentduration = findViewById(R.id.txtInstallmentduration);
        txtDescription = findViewById(R.id.txtDescription);

        txtDownpayment = findViewById(R.id.txtDownpayment);
        txtDelinquent = findViewById(R.id.txtDelinquent);

        btnAssume = findViewById(R.id.btnAssume); btnBack = findViewById(R.id.btnBack);
        this.common = new Common();
    }

    private void initControllerAPI() {
        PropertyViewCertainController pvc = new PropertyViewCertainController(this, this);
        pvc.getCertainVehicleProperty(propertyID);
    }
    @Override
    public void getCertainProperty(GenericClass genericClass) {
        Object obj = genericClass.getCertainClass();
        if(obj instanceof PVCVehicleModel) {
            String images = ((PVCVehicleModel) obj).vehicle.get(0).getVehicleIMAGES();
            String[] imagesArr = images.substring(1, images.length()-1).split(",");

            List<SlideModel> slideModelList = new ArrayList<>();
            int imgLen = imagesArr.length;

            for(int i = 0; i < imgLen; i++) {
                slideModelList.add(new SlideModel(common.getApiURI()+"/"+imagesArr[i].replaceAll("\"", ""), ScaleTypes.FIT));
            }
            imageSlider.setImageList(slideModelList);

            txtStatus.setText("Status: "+((PVCVehicleModel) obj).vehicle.get(0).getPropertyStatus());
            txtOwner.setText("Owner: "+((PVCVehicleModel) obj).vehicle.get(0).getVehicleOwner());
            txtContactno.setText("Contactno: "+((PVCVehicleModel) obj).vehicle.get(0).getVehicleContactno());
            txtBrand.setText("Brand: "+((PVCVehicleModel) obj).vehicle.get(0).getVehicleBrand());
            txtModel.setText("Model: "+((PVCVehicleModel) obj).vehicle.get(0).getVehicleModel());
            txtLocation.setText("Location: "+((PVCVehicleModel) obj).vehicle.get(0).getVehicleLocation());
            txtDownpayment.setText("Downpayment: "+((PVCVehicleModel) obj).vehicle.get(0).getVehicleDownpayment());

            txtInstallmentpaid.setText("Installmentpaid: "+((PVCVehicleModel) obj).vehicle.get(0).getVehicleInstallmentPaid());
            txtInstallmentduration.setText("Duration: "+((PVCVehicleModel) obj).vehicle.get(0).getVehicleInstallmentDuration());
            txtDelinquent.setText("Delinquent: "+((PVCVehicleModel) obj).vehicle.get(0).getVehicleDelinquent());
            txtDescription.setText("Description: "+((PVCVehicleModel) obj).vehicle.get(0).getDescription());
        }
    }
}
