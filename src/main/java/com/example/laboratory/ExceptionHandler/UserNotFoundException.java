package com.example.laboratory.ExceptionHandler;

public class UserNotFoundException extends UserException{
    public UserNotFoundException(String message)
    {
        super(message);
    }
    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
