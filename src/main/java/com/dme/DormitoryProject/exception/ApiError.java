package com.dme.DormitoryProject.exception;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class ApiError<T> {
    private String id;
    private Date errorDate;
    private T errors;


    public ApiError(String id, Date errorDate, T errors) {
        this.id = id;
        this.errorDate = errorDate;
        this.errors = errors;
    }

    public ApiError(){

    }

    public T getErrors() {
        return errors;
    }

    public void setErrors(T errors) {
        this.errors = errors;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getErrorDate() {
        return errorDate;
    }

    public void setErrorDate(Date errorDate) {
        this.errorDate = errorDate;
    }

}
