package com.example.laboratory.ExceptionHandler;

public abstract class UserException extends RuntimeException{
    public UserException(String message) {
        super(message);
    }
}
