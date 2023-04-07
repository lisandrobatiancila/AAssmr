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

public class ViewCertainVehicle extends AppCompatActivity {
    ImageSlider imageSlider;
    Common common;
    TextView txtOwner, txtContactno, txtBrand, txtModel, txtLocation, txtInstallmentpaid;
    TextView txtDownpayment, txtInstallmentDuration, txtDelinquent, txtDescription;
    TextView txtAssumptionCount, txtStatus;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_certain_vehicle);

        initializedForm();

    }
    public void initializedForm() {
        common = new Common();
        imageSlider = findViewById(R.id.imgSlider);
        txtOwner = findViewById(R.id.txtOwner);
        txtContactno = findViewById(R.id.txtContactno);
        txtBrand = findViewById(R.id.txtBrand);
        txtModel = findViewById(R.id.txtModel);
        txtLocation = findViewById(R.id.txtLocation);
        txtDownpayment = findViewById(R.id.txtDownpayment);
        txtInstallmentpaid = findViewById(R.id.txtInstallmentpaid);
        txtInstallmentDuration = findViewById(R.id.txtInstallmentduration);
        txtDelinquent = findViewById(R.id.txtDelinquent);
        txtDescription = findViewById(R.id.txtDescription);
        txtAssumptionCount = findViewById(R.id.txtAssumptionCount);
        txtStatus = findViewById(R.id.txtStatus);

        List<SlideModel> imageList = new ArrayList<>();

        Bundle bundle = getIntent().getExtras();
        String image = bundle.getString("image");
        String[] imageArray = image.substring(1, image.length()-1).split(",");
        int imgLen = imageArray.length;

        for(int i = 0; i < imgLen; i++) {
            imageList.add(new SlideModel(common.getApiURI()+"/"+imageArray[i].replaceAll("\"", ""), ScaleTypes.FIT));
        }
        imageSlider.setImageList(imageList);
        txtOwner.setText(bundle.getString("owner"));
        txtContactno.setText(bundle.getString("contactno"));
        txtBrand.setText(bundle.getString("brand"));
        txtModel.setText(bundle.getString("model"));
        txtLocation.setText(bundle.getString("location"));
        txtDownpayment.setText(bundle.getString("downpayment"));
        txtInstallmentpaid.setText(bundle.getString("installmentpaid"));
        txtInstallmentDuration.setText(bundle.getString("installmentduration"));
        txtDelinquent.setText(bundle.getString("delinquent"));
        txtDescription.setText(bundle.getString("description"));

        txtAssumptionCount.setText("Total assumed: "+bundle.getInt("assumptionCount"));
        txtStatus.setText("Status: "+bundle.getString("propertyStatus"));
    }
}
