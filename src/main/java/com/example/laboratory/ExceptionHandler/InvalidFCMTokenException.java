package com.example.laboratory.ExceptionHandler;

public class InvalidFCMTokenException extends UserException{
    public InvalidFCMTokenException(String message) {
        super(message);
    }

    @Override
    public String getMessage()
    {
        return super.getMessage();
    }
}
