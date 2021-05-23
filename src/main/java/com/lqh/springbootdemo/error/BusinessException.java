package com.lqh.springbootdemo.error;

public class BusinessException extends RuntimeException {
    public BusinessException(){}

    public BusinessException(String message){
        super(message);
    }
}
