package com.example.webblog.model;

public class TopicRequestModel {
    private String name;

    public TopicRequestModel() {
    }

    public TopicRequestModel(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
