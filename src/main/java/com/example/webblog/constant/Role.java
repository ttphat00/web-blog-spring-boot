package com.example.webblog.constant;

public enum Role {
    NORMAL_USER("normal-user"),
    ADMIN("admin");

    private String name;

    Role(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
