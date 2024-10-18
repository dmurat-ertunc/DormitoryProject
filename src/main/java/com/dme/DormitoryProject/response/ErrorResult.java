package com.dme.DormitoryProject.response;

public class ErrorResult extends Result{
    public ErrorResult(String message,Boolean status){
        super(message,true);
    }
}
