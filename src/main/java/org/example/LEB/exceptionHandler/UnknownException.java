package org.example.LEB.exceptionHandler;

public class UnknownException extends  RuntimeException{
    public UnknownException(String message){
        super(message);
    }
}
