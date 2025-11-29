package com.example.EmpMS.customException;

public class EmployeeNotFoundExecption extends RuntimeException{
    public EmployeeNotFoundExecption(String message){
        super(message);
    }
}
