package com.example.laboratory.Response;

public class AuthResponse {
    private String data;
    private String message;

    public AuthResponse(String token, String message) {
        this.data = token;
        this.message = message;
    }

    // Getters and Setters
    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
