package com.avatarduel.util;

public class ErrorException extends Exception{
    private String message;

    public ErrorException(String message){
        this.message = message;
    }
    
    public String getMessage(){
        return this.message;
    }
}
