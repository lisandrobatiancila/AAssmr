package com.example.a_assmr.Views.Credentials.Account.My_Property.ViewProperty.VURCertainProperty;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.a_assmr.Common;
import com.example.a_assmr.R;

import java.util.ArrayList;
import java.util.List;

public class ViewCertainJewelry extends AppCompatActivity {
    ImageSlider imageSlider;
    Common common;
    TextView txtOwner, txtContactno, txtJewelryName, txtModel, txtLocation, txtInstallmentpaid;
    TextView txtDownpayment, txtInstallmentDuration, txtDelinquent, txtDescription;
    TextView txtAssumptionCount, txtStatus;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_certain_jewelry);

        initializedFields();
    }
    public void initializedFields() {
        imageSlider = findViewById(R.id.imgSlider);
        txtOwner = findViewById(R.id.txtOwner); txtContactno = findViewById(R.id.txtContactno);
        txtJewelryName = findViewById(R.id.txtJName); txtModel = findViewById(R.id.txtModel);
        txtLocation = findViewById(R.id.txtLocation); txtInstallmentpaid = findViewById(R.id.txtInstallmentpaid);
        txtDownpayment = findViewById(R.id.txtDownpayment); txtInstallmentDuration = findViewById(R.id.txtInstallmentduration);
        txtDelinquent = findViewById(R.id.txtDelinquent); txtDescription = findViewById(R.id.txtDescription);
        txtAssumptionCount = findViewById(R.id.txtAssumptionCount); txtStatus = findViewById(R.id.txtStatus);

        this.common = new Common();

        Bundle bundle = getIntent().getExtras();

        String image = bundle.getString("image");
        String[] imageArray = image.substring(1, image.length()-1).split(",");

        List<SlideModel> slideModelList = new ArrayList<>();
        int imgLen = imageArray.length;
        for(int i = 0; i < imgLen; i++) {
            slideModelList.add(new SlideModel(this.common.getApiURI()+"/"+imageArray[i].replaceAll("\"", ""), ScaleTypes.FIT));
        } // end of for loop
        imageSlider.setImageList(slideModelList);

        txtOwner.setText(bundle.getString("owner"));
        txtContactno.setText(bundle.getString("contactno"));
        txtJewelryName.setText(bundle.getString("jewelry_name"));
        txtModel.setText(bundle.getString("jewelry_model"));
        txtLocation.setText(bundle.getString("location"));
        txtDownpayment.setText(bundle.getString("downpayment"));
        txtInstallmentpaid.setText(bundle.getString("paid"));
        txtInstallmentDuration.setText(bundle.getString("duration"));
        txtDescription.setText(bundle.getString("description"));
        txtAssumptionCount.setText("Total assumed: "+bundle.getInt("assumptionCount"));
        txtStatus.setText("Status: "+bundle.getString("propertyStatus"));
    }
}
