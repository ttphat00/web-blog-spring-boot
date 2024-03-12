package com.example.webblog.model;

import java.util.Date;

public class AuthResponseModel {
    private String fullName;
    private String username;
    private String email;
    private String avatar;
    private Date createdDate;
    private TokenModel token;

    public AuthResponseModel() {
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public TokenModel getToken() {
        return token;
    }

    public void setToken(TokenModel token) {
        this.token = token;
    }
}
