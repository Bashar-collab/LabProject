package com.example.laboratory.ExceptionHandler;

public class UserAlreadyExistsException extends UserException{
    public UserAlreadyExistsException(String message)
    {
        super(message);
    }

    @Override
    public String getMessage()
    {
        return super.getMessage();
    }
}
