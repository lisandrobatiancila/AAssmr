package com.example.a_assmr.Views.Credentials.Properties.PropertyLists;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.a_assmr.Views.Credentials.Properties.PropertyLists.Jewelry.JewelryFragment;
import com.example.a_assmr.Views.Credentials.Properties.PropertyLists.Realestate.RealestateFragment;
import com.example.a_assmr.Views.Credentials.Properties.PropertyLists.Vehicle.VehicleFragment;

public class FragmentPageAdapter extends FragmentStateAdapter {
    public FragmentPageAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new VehicleFragment(); // default fragment
            case 1:
                return new RealestateFragment();
            case 2:
                return new JewelryFragment();
            default:
                return new VehicleFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
