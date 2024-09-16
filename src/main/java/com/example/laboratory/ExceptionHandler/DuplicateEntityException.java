package com.example.laboratory.ExceptionHandler;

public class DuplicateEntityException extends RuntimeException{
    public DuplicateEntityException(String message)
    {
        super(message);
    }
}
