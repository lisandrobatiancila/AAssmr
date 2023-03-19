package com.example.a_assmr.CommonDir;

public class GenericClass<T> {
    public GenericClass() {

    }
    private T certainCalss;
    public void setCertainClass(T certainCalss) {
        this.certainCalss = certainCalss;
    }
    public T getCertainClass() {
        return this.certainCalss;
    }
}
/* this class is used to reset the form from child TO parent
  We cant send a trigger response to the parent class if some child class has done process the request
*/