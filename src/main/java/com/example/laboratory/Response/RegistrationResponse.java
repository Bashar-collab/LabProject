package com.example.laboratory.Response;


public class RegistrationResponse {
    private long id;
    private String message;

    public RegistrationResponse(Long userId, String message) {
        if(userId != null)
            this.id = userId;
        this.message = message;
    }

    // Getters and setters


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
