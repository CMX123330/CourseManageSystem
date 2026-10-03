package com.cmx.cms.model;

public class User {
    private String userId;
    private String password;
    private String role;
    private String name;

    public User() {
    }

    public User(String userId, String password, String role, String name) {
        this.userId = userId;
        this.password = password;
        this.role = role;
        this.name = name;
    }

    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role = role;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
