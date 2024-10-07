package com.dme.DormitoryProject.response;

public class MyResponseEntity<T> {
    private boolean status;
    private String message;
    private T data;
    // Constructor
    public MyResponseEntity(boolean status, String message, T data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }
    public MyResponseEntity(){

    }
    // Getter ve Setter metodları
    public boolean getStatus() {
        return status;
    }
    public void setStatus(boolean status) {
        this.status = status;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public T getData() {
        return data;
    }
    public void setData(T data) {
        this.data = data;
    }
}
