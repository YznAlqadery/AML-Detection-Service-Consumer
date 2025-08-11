package com.yzn.transaction_consumer.model.dto;

import com.yzn.transaction_consumer.model.enums.Role;

public class LoginResponse {
    private String username;
    private String jwtToken;
    private Role role;

    public LoginResponse(String username, String jwtToken, Role role) {
        this.username = username;
        this.jwtToken = jwtToken;
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getJwtToken() {
        return jwtToken;
    }

    public void setJwtToken(String jwtToken) {
        this.jwtToken = jwtToken;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
