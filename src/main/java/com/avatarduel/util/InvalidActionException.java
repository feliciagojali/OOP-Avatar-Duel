package com.avatarduel.util;

/**
 * InvalidActionException class extends exception so
 * it can be thrown in situations when player action is invalid
 */
public class InvalidActionException extends Exception{
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private String message;

    public InvalidActionException(String message){
        this.message = message;
    }
    
    public String getMessage(){
        return this.message;
    }
}
