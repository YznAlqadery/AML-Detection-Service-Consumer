package com.yzn.transaction_consumer.model.dto;

public class LoginResponse {
    private String username;
    private String JwtToken;
    private String role;

    public LoginResponse(String username, String jwtToken,String role) {
        this.username = username;
        this.JwtToken = jwtToken;
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getJwtToken() {
        return JwtToken;
    }

    public void setJwtToken(String jwtToken) {
        JwtToken = jwtToken;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
