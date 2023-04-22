package com.example.a_assmr.Views.Credentials.Properties.View;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

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
import com.example.a_assmr.Views.Credentials.Properties.View.Model.PVCJewelryModel;

import java.util.ArrayList;
import java.util.List;

public class PropertyViewCertainJewelry extends AppCompatActivity implements PVCInterface {
    SwipeRefreshLayout swipeRefreshLayout;
    ImageSlider slider;
    TextView txtOwner, txtContactno, txtJewelryName, txtJewelryType, txtLocation;
    TextView txtDownpayment, txtInstallmentpaid, txtInstallmentduration, txtDelinquent;
    TextView txtStatus;
    TextView txtDescription;
    Button btnAssume, btnBack;
    PropertyViewCertainController pvcc;
    private int propertyID;
    Common common;
    PropertyViewCertainJewelry self;
    AlertDialog dialog;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.property_view_certain_jewelry);

        initForm();
        initControllerAPI();

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.setRefreshing(false);
                initControllerAPI();
            }
        });
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
        });
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PropertyViewCertainJewelry.this.onBackPressed();
            }
        });
    }
    public void initForm() {
        Bundle bundle = getIntent().getExtras();
        self = PropertyViewCertainJewelry.this;
        propertyID = bundle.getInt("property_id");
        common = new Common();

        swipeRefreshLayout = findViewById(R.id.swipeRefreshLayout);
        slider = findViewById(R.id.imgSlider);
        txtOwner = findViewById(R.id.txtOwner); txtContactno = findViewById(R.id.txtContactno);
        txtJewelryName = findViewById(R.id.txtJewelryName); txtJewelryType = findViewById(R.id.txtJewelryType);
        txtLocation = findViewById(R.id.txtLocation); txtDownpayment = findViewById(R.id.txtDownpayment);
        txtInstallmentpaid = findViewById(R.id.txtInstallmentpaid); txtInstallmentduration = findViewById(R.id.txtInstallmentduration);
        txtDelinquent = findViewById(R.id.txtDelinquent); txtDescription = findViewById(R.id.txtDescription);

        txtStatus = findViewById(R.id.txtStatus);

        btnAssume = findViewById(R.id.btnAssume); btnBack = findViewById(R.id.btnBack);
    }
    public void initControllerAPI() {
        pvcc = new PropertyViewCertainController(this, this);
        pvcc.getCertainJewelryProperty(propertyID);
    }

    @Override
    public void getCertainProperty(GenericClass genericClass) {
        Object object = genericClass.getCertainClass();
        if(object instanceof PVCJewelryModel) {
            if(((PVCJewelryModel) object).getCode() == 200) {
                txtStatus.setText("Status: "+((PVCJewelryModel) object).getJewelry().get(0).getPropertyStatus());
                txtOwner.setText("Owner: "+((PVCJewelryModel) object).getJewelry().get(0).getJewelryOwner());
                txtContactno.setText("Contactno: "+((PVCJewelryModel) object).getJewelry().get(0).getJewelryContactno());
                txtJewelryName.setText("Jewelry Name: "+((PVCJewelryModel) object).getJewelry().get(0).getJewelryName());
                txtJewelryType.setText("Jewelry Type: "+((PVCJewelryModel) object).getJewelry().get(0).getJewelryModel());
                txtLocation.setText("Location: "+((PVCJewelryModel) object).getJewelry().get(0).getJewelryLocation());
                txtDownpayment.setText("Downpayment: "+((PVCJewelryModel) object).getJewelry().get(0).getJewelryDownpayment());
                txtInstallmentpaid.setText("Installmentpaid: "+((PVCJewelryModel) object).getJewelry().get(0).getJewelryInstallmentpaid());
                txtInstallmentduration.setText("Installmentduration: "+((PVCJewelryModel) object).getJewelry().get(0).getJewelryInstallmentpaid());
                txtDelinquent.setText("Delinquent: "+((PVCJewelryModel) object).getJewelry().get(0).getJewelryDelinquent());
                txtDescription.setText("Description: "+((PVCJewelryModel) object).getJewelry().get(0).getJewelryDescription());

                String image = ((PVCJewelryModel) object).getJewelry().get(0).getJewelryIMG();
                String[] imageArray = image.substring(1, image.length()-1).split(",");
                final int imgLen = imageArray.length;
                List<SlideModel> slideModelList = new ArrayList<>();

                for(int i = 0; i < imgLen; i++) {
                    slideModelList.add(new SlideModel(common.getApiURI()+"/"+imageArray[i].replaceAll("\"", ""), ScaleTypes.FIT));
                }
                slider.setImageList(slideModelList);
            }
            else
                Toast.makeText(this, ((PVCJewelryModel) object).getMessage(), Toast.LENGTH_LONG).show();
        }
    }
}
