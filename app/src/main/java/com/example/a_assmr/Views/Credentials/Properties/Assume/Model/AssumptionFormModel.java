package com.example.a_assmr.Views.Credentials.Properties.Assume.Model;

public class AssumptionFormModel {
    int userID, propertyID;
    String firstname, middlename, lastname, contactno, address;
    String work, salary;

    public AssumptionFormModel(int userID, int propertyID, String firstname, String middlename, String lastname, String contactno, String address, String work, String salary) {
        this.userID = userID;
        this.propertyID = propertyID;
        this.firstname = firstname;
        this.middlename = middlename;
        this.lastname = lastname;
        this.contactno = contactno;
        this.address = address;
        this.work = work;
        this.salary = salary;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getPropertyID() {
        return propertyID;
    }

    public void setPropertyID(int propertyID) {
        this.propertyID = propertyID;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getMiddlename() {
        return middlename;
    }

    public void setMiddlename(String middlename) {
        this.middlename = middlename;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getContactno() {
        return contactno;
    }

    public void setContactno(String contactno) {
        this.contactno = contactno;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getWork() {
        return work;
    }

    public void setWork(String work) {
        this.work = work;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }
}
