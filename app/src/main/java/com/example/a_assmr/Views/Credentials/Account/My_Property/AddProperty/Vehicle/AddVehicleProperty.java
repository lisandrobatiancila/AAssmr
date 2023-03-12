package com.example.a_assmr.Views.Credentials.Account.My_Property.AddProperty.Vehicle;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.example.a_assmr.R;

public class AddVehicleProperty extends Fragment {
    ImageButton imageButton;
    TextView txtFileCount;
    @SuppressLint("MissingInflatedId")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.account_add_vehicle_property, container, false);
        imageButton = view.findViewById(R.id.imgbtnOpenGallery);
        txtFileCount = view.findViewById(R.id.txtFileCount);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i_open_gallery = new Intent(Intent.ACTION_GET_CONTENT);
                i_open_gallery.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
                i_open_gallery.setType("image/*");

                galleryActivityResultLauncher.launch(i_open_gallery);
            }
        });
        return view;
    }
    private final ActivityResultLauncher<Intent> galleryActivityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @SuppressLint("ResourceAsColor")
                @Override
                public void onActivityResult(ActivityResult result) {
                    if(result.getResultCode() == Activity.RESULT_OK) {
                        Intent data = result.getData();
                        txtFileCount.setText("You included ( "+data.getClipData().getItemCount()+" ) file(s)");
                        txtFileCount.setTextColor(R.color.teal_200);
                    }
                    else {
                        Toast.makeText(getContext(), "Gallery permission is denied.", Toast.LENGTH_SHORT).show();
                    }
                }
            }
    );
    private boolean checkStoragePermission() {
        boolean result = ContextCompat.checkSelfPermission(getContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED;
        return result;
    }
    private void openGallery() {

    }
}
