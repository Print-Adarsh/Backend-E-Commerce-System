package com.example.projectname.exception;

import org.aspectj.bridge.IMessage;

public class UnAuthorizedException extends  Exception{
    private String message;
    public UnAuthorizedException(String message){
        super(message);
        this.message=message;

    }
}
