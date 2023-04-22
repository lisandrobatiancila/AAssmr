package com.example.a_assmr.Views.Credentials.Properties.View;

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
import com.example.a_assmr.CommonDir.GenericClass;
import com.example.a_assmr.R;
import com.example.a_assmr.Views.Credentials.Properties.View.Controller.PropertyViewCertainController;
import com.example.a_assmr.Views.Credentials.Properties.View.Interface.PVCInterface;
import com.example.a_assmr.Views.Credentials.Properties.View.Model.PVCRealestateModel;

import java.util.ArrayList;
import java.util.List;

public class PropertyViewCertainRealestate extends AppCompatActivity implements PVCInterface {
    TextView txtOwner, txtContactno, txtPropertyType, txtRealestateType, txtLocation, txtDownpayment;
    TextView txtInstallmentpaid, txtInstallmentduration, txtDelinquent, txtDescription;
    TextView txtStatus;
    ImageSlider imageSlider;
    SwipeRefreshLayout swipeRefreshLayout;
    Button btnAssume, btnBack;
    private int propertyID;
    PropertyViewCertainController pvcc;
    Common common;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.property_view_certain_realestate);

        initFields();
        initControllerAPI();

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.setRefreshing(false);
                initControllerAPI();
            }
        });
    }
    public void initFields() {
        common = new Common();
        swipeRefreshLayout = findViewById(R.id.swipeRefreshLayout);

        txtOwner = findViewById(R.id.txtOwner); txtContactno = findViewById(R.id.txtContactno); txtPropertyType = findViewById(R.id.txtPropType);
        txtRealestateType = findViewById(R.id.txtRealType); txtLocation = findViewById(R.id.txtLocation);
        txtInstallmentpaid = findViewById(R.id.txtInstallmentpaid); txtInstallmentduration = findViewById(R.id.txtInstallmentduration);
        txtDelinquent = findViewById(R.id.txtDelinquent); txtDescription = findViewById(R.id.txtDescription);
        txtDownpayment = findViewById(R.id.txtDownpayment); txtStatus = findViewById(R.id.txtStatus);

        imageSlider = findViewById(R.id.imgSlider);

        btnAssume = findViewById(R.id.btnAssume); btnBack = findViewById(R.id.btnBack);

        Bundle bundle = getIntent().getExtras();
        propertyID = bundle.getInt("property_id");

        pvcc = new PropertyViewCertainController(this, this);

        btnAssume.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PropertyViewCertainRealestate.this.onBackPressed();
            }
        });
    }
    public void initControllerAPI() {
        pvcc.getCertainRealestateProperty(propertyID);
    }

    @Override
    public void getCertainProperty(GenericClass genericClass) {
        Object obj = genericClass.getCertainClass();
        if(obj instanceof PVCRealestateModel) {
            if(((PVCRealestateModel) obj).getCode() == 200) {
                txtStatus.setText("Status: "+((PVCRealestateModel) obj).getRealestate().get(0).getPropertyStatus());
                txtOwner.setText("Owner: "+((PVCRealestateModel) obj).getRealestate().get(0).getRealestateOwner());
                txtContactno.setText("Contactno: "+((PVCRealestateModel) obj).getRealestate().get(0).getRealestateContactno());
                txtPropertyType.setText("Property Type: Realesate");
                txtRealestateType.setText("Realestate Type: "+((PVCRealestateModel) obj).getRealestate().get(0).getRealestateType());
                txtLocation.setText("Location: "+((PVCRealestateModel) obj).getRealestate().get(0).getRealestateLocation());
                txtDownpayment.setText("Downpayment: "+((PVCRealestateModel) obj).getRealestate().get(0).getRealestateDownpayment());
                txtInstallmentpaid.setText("Installment Paid: "+((PVCRealestateModel) obj).getRealestate().get(0).getRealestateInstallmentpaid());
                txtInstallmentduration.setText("Installment Duration: "+((PVCRealestateModel) obj).getRealestate().get(0).getRealestateInstallmentduration());
                txtDelinquent.setText("Delinquent: "+((PVCRealestateModel) obj).getRealestate().get(0).getRealestateDelinquent());
                txtDescription.setText("Description: "+((PVCRealestateModel) obj).getRealestate().get(0).getRealestateDescription());

                String image = ((PVCRealestateModel) obj).getRealestate().get(0).getRealestateIMG();
                String[] imageArray = image.substring(1, image.length()-1).split(",");

                List<SlideModel> slideModelList = new ArrayList<>();
                final int imgArrLen = imageArray.length;

                for(int i = 0; i < imgArrLen; i++) {
                    slideModelList.add(new SlideModel(common.getApiURI()+"/"+imageArray[i].replaceAll("\"", ""), ScaleTypes.FIT));
                }
                imageSlider.setImageList(slideModelList);
            }
            else
                Toast.makeText(this, "Something went wrong", Toast.LENGTH_LONG).show();
        }
    }
}
