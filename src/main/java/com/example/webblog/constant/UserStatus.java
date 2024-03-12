package com.example.webblog.constant;

public enum UserStatus {
    ACTIVE("active"),
    DELETED("deleted"),
    BLOCKED("blocked");

    private String name;

    UserStatus(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
