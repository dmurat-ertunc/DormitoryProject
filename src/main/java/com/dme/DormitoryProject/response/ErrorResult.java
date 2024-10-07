package com.dme.DormitoryProject.response;

public class ErrorResult extends DataResult{
    public ErrorResult(String message,Boolean status){
        super(message,status);
    }
}
