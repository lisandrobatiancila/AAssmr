package com.example.a_assmr.Views.Credentials.Account.My_Property.ViewProperty;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.a_assmr.CommonDir.GenericClassServerResponse;

public class ItemViewModel extends ViewModel {
    private final MutableLiveData<GenericClassServerResponse> selectedItem = new MutableLiveData<>();

    public void selectItem(GenericClassServerResponse genericClass) {
        selectedItem.setValue(genericClass);
    }
    public LiveData<GenericClassServerResponse> getSelectedItem() {
        return selectedItem;
    }
}
