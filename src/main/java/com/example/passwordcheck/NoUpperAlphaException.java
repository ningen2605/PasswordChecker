package com.example.passwordcheck;

public class NoUpperAlphaException extends Exception{
    public NoUpperAlphaException(String message){
        super(message);
    }
}
