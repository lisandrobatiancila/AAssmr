package com.example.a_assmr.Views.Credentials.Account.My_Property.ViewProperty.VURCertainProperty.Model;

public class UpdateVehicleModel {
    int propertyID;
    String owner, contactno, brand, model, location, downpayment, installmentpaid, installmentduration;
    String delinquent, description;

    public UpdateVehicleModel(int propertyID, String owner, String contactno, String brand, String model, String location, String downpayment, String installmentpaid, String installmentduration, String delinquent, String description) {
        this.propertyID = propertyID;
        this.owner = owner;
        this.contactno = contactno;
        this.brand = brand;
        this.model = model;
        this.location = location;
        this.downpayment = downpayment;
        this.installmentpaid = installmentpaid;
        this.installmentduration = installmentduration;
        this.delinquent = delinquent;
        this.description = description;
    }
}
