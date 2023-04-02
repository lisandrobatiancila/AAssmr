package com.example.a_assmr.CommonDir;

public class GenericClassServerResponse<T> {
    private T certainGenericClass;
    public GenericClassServerResponse() {}

    public void setCertainGenericClassServerResponse(T certainGenericClassServerResponse) {
        this.certainGenericClass = certainGenericClassServerResponse;
    }
    public T getCertainGenericClass() {
        return this.certainGenericClass;
    }
}
