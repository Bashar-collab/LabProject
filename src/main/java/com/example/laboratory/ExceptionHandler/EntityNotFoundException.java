package com.example.laboratory.ExceptionHandler;

public class EntityNotFoundException extends RuntimeException{
    public EntityNotFoundException(String message)
    {
        super(message);
    }
}
