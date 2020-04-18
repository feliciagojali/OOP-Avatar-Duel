package com.avatarduel.util;

public class InvalidActionException extends Exception{
    private String message;

    public InvalidActionException(String message){
        this.message = message;
    }
    
    public String getMessage(){
        return this.message;
    }
}
