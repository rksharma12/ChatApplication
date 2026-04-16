package com.example.demo.dtos;

public class SignUpRespone {
    private Boolean err;
    private String error;
    public SignUpRespone(){};

    public SignUpRespone setError(String error) {
        this.error = error;
        return this;
    }

    public String getError() {
        return error;
    }

    public SignUpRespone setErr(Boolean err) {
        this.err = err;
        return this;
    }
}
