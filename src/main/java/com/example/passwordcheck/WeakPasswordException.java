package com.example.passwordcheck;

public class WeakPasswordException extends Exception {
    public WeakPasswordException(String message){
        super(message);
    }
}
