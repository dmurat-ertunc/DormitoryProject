package com.dme.DormitoryProject.response;

public class SuccessDataResult<T> extends DataResult{

    public SuccessDataResult(String message, boolean status, T data){
        super(message, true, data);
    }

}
