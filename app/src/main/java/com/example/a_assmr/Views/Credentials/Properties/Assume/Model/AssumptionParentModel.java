package com.example.a_assmr.Views.Credentials.Properties.Assume.Model;

import com.example.a_assmr.Views.Credentials.Signup.Model.Cities;

import java.util.List;

public class AssumptionParentModel {
    public List<AssumerInfoModel> assumerInfoModels;
    public List<Cities> addressInfoModels;

    public AssumptionParentModel(List<AssumerInfoModel> assumerInfoModels, List<Cities> addressInfoModels) {
        this.assumerInfoModels = assumerInfoModels;
        this.addressInfoModels = addressInfoModels;
    }

    public List<AssumerInfoModel> getAssumerInfoModels() {
        return assumerInfoModels;
    }

    public void setAssumerInfoModels(List<AssumerInfoModel> assumerInfoModels) {
        this.assumerInfoModels = assumerInfoModels;
    }

    public List<Cities> getAddressInfoModels() {
        return addressInfoModels;
    }

    public void setAddressInfoModels(List<Cities> addressInfoModels) {
        this.addressInfoModels = addressInfoModels;
    }
}
