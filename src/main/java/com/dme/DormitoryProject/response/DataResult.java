package com.dme.DormitoryProject.response;

public class DataResult<T> extends Result{

    private T data;

    public DataResult(String message, Boolean status, T data) {
        super(message, status);
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
