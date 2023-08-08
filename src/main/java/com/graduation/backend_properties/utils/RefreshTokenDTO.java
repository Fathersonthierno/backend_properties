package com.graduation.backend_properties.utils;

public class RefreshTokenDTO {

    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public RefreshTokenDTO(String token) {
        this.token = token;
    }
}
