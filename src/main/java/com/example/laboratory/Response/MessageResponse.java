package com.example.laboratory.Response;

public class MessageResponse {
    private String message;
    //    private String field;
    public MessageResponse(String message)
    {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
