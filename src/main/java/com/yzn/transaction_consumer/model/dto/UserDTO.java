package com.yzn.transaction_consumer.model.dto;

import com.yzn.transaction_consumer.model.enums.Role;

public class UserDTO {
    private Integer id;
    private String username;
    private Role role;

    public UserDTO() {
    }

    public UserDTO(String username, Role role) {
        this.username = username;
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

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", role=" + role +
                '}';
    }
}
