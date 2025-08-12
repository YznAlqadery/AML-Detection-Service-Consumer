package com.yzn.transaction_consumer.model.dto;

import com.yzn.transaction_consumer.model.enums.Role;

public class LoginResponse {
    Integer id;
    private String username;
    private String jwtToken;
    private Role role;

    public LoginResponse(Integer id,String username, String jwtToken, Role role) {
        this.id = id;
        this.username = username;
        this.jwtToken = jwtToken;
        this.role = role;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
