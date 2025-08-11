package com.yzn.transaction_consumer.model.dto;
import com.yzn.transaction_consumer.model.enums.Role;
import jakarta.validation.constraints.NotBlank;


public class LoginRequest {

    @NotBlank(message = "Username is required")
    private String username;

    @NotBlank(message = "Password is required")
    private String password;

    private Role role;

    public LoginRequest() {
    }

    public LoginRequest(String username, String password,Role role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
