package com.example.webblog.constant;

public enum PostStatus {
    PENDING("pending"),
    PASSED("passed"),
    BLOCKED("blocked"),
    DELETED("deleted");

    private String name;

    PostStatus(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
