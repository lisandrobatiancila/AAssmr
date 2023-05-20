package com.example.a_assmr.Views.Credentials.Account.My_Property.Inquiries.MyAssumptions.PropertyDetails;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.a_assmr.Common;
import com.example.a_assmr.CommonDir.ActiveUserSharedPref;
import com.example.a_assmr.R;
import com.example.a_assmr.Views.Credentials.Account.My_Property.Inquiries.MyAssumptions.Controller.MyAssumptionController;
import com.example.a_assmr.Views.Credentials.Account.My_Property.Inquiries.MyAssumptions.PropertyDetails.Model.PropertyDetailsModel;
import com.example.a_assmr.Views.Credentials.Account.My_Property.ViewProperty.ItemViewModel;

import java.util.ArrayList;
import java.util.List;

public class PropertyDetails extends AppCompatActivity {
    ImageSlider imageSlider;
    TextView txtAssumptionCount, txtOwner, txtContactno, txtLocation, txtDownpayment, txtDuration, txtDelinquent, txtDescription;
    MyAssumptionController myAssumptionController;
    ItemViewModel itemViewModel;
    Common common;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.accnt_inqrs_my_inqrs_prop_details);
        setTitle("Property Details");

        initForm();

    }
    private void initForm() {
        itemViewModel = new ViewModelProvider(this).get(ItemViewModel.class);
        common = new Common();

        imageSlider = findViewById(R.id.imgSlider);

        txtAssumptionCount = findViewById(R.id.txtAssumptionCount);
        txtOwner = findViewById(R.id.txtOwner); txtContactno = findViewById(R.id.txtContactno); txtLocation = findViewById(R.id.txtLocation);
        txtDownpayment = findViewById(R.id.txtDownpayment); txtDuration = findViewById(R.id.txtDuration); txtDelinquent = findViewById(R.id.txtDelinquent);
        txtDescription = findViewById(R.id.txtDescription);

        Bundle bundle = getIntent().getExtras();
        int itemID = bundle.getInt("itemID"); // means the vehicleID or realestateID or jewelryID
        int propertyID = bundle.getInt("propID"); // means the propertyID
        String propertyType = bundle.getString("propertyType");

        myAssumptionController = new MyAssumptionController(this, PropertyDetails.this);
        myAssumptionController.getMyCertainAssumption(new ActiveUserSharedPref(this).activeUserID(), propertyType, propertyID, itemID);

        itemViewModel.getSelectedItem().observe(this, object -> {
            Object obj = object.getCertainGenericClass();
            if(obj instanceof PropertyDetailsModel) {
                List<SlideModel> imageLists  = new ArrayList<>();

                txtAssumptionCount.setText("You +"+(((PropertyDetailsModel) obj).getCertainProperty().get(0).getAssumptionCount()-1)+" Other assume this.");
                txtOwner.setText("Owner: "+((PropertyDetailsModel) obj).getCertainProperty().get(0).getOwner());
                txtContactno.setText("Contactno: "+((PropertyDetailsModel) obj).getCertainProperty().get(0).getContactno());
                txtLocation.setText("Location: "+((PropertyDetailsModel) obj).getCertainProperty().get(0).getLocation());
                txtDownpayment.setText("Downpayment: "+((PropertyDetailsModel) obj).getCertainProperty().get(0).getDownpayment());
                txtDuration.setText("Duration: "+((PropertyDetailsModel) obj).getCertainProperty().get(0).getDuration());
                txtDelinquent.setText("Delinquent: "+((PropertyDetailsModel) obj).getCertainProperty().get(0).getDelinquent());
                txtDescription.setText("Description: "+((PropertyDetailsModel) obj).getCertainProperty().get(0).getDescription());

                String image = ((PropertyDetailsModel) obj).getCertainProperty().get(0).getImg();
                String[] imageArr = image.substring(1, image.length()-1).split(",");
                final int imgArrLen = imageArr.length;

                for(int i = 0; i < imgArrLen; i++) {
                    imageLists.add(new SlideModel(common.getApiURI()+"/"+imageArr[i].replaceAll("\"", ""), ScaleTypes.FIT));
                }
                imageSlider.setImageList(imageLists);
            }
        });
    }
}
