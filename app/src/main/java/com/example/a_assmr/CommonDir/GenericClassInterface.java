package com.example.a_assmr.CommonDir;

public interface GenericClassInterface<T> {
    public void resetForm(GenericClass<T> genericClass);
}
/* this interface is used to reset the form from child TO parent
  We cant send a trigger response to the parent class if some child class has done process the request
  So we create a GenericClassInterface, and try if this can solve our issue.
*/