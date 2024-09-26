package com.example.laboratory.Response;

public class JwtResponse {
    private final String token;

    public JwtResponse(String token) {
        this.token = token;
    }

    // Getter for token
    public String getToken() {
        return token;
    }
}
