package com.example.demo.dtos;

public class LoginResponse {
    private String token;

    private long expiresIn;
    private Boolean exc;
    private String error;
    public String getToken() {
        return token;
    }

    public LoginResponse setToken(String token) {
        this.token = token;
        return this;
    }

    public LoginResponse setExc(Boolean exc) {
        this.exc = exc;
        return this;
    }

    public Boolean getExc() {
        return exc;
    }

    public LoginResponse setExpiresIn(long expiresIn) {
        this.expiresIn = expiresIn;
        return this;
    }

    public String getError() {
        return error;
    }

    public LoginResponse setError(String error) {
        this.error = error;
        return this;
    }
    // Getters and setters...
}
