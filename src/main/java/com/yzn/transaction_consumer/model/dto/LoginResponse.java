package com.yzn.transaction_consumer.model.dto;

import com.yzn.transaction_consumer.model.enums.Role;

public class LoginResponse {
    private String username;
    private String JwtToken;
    private Role role;

    public LoginResponse(String username, String jwtToken,Role role) {
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

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
