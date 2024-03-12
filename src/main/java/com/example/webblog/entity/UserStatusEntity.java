package com.example.webblog.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "user_status")
public class UserStatusEntity extends BaseEntity {
    @Column(nullable = false, unique = true)
    private String name;

    public UserStatusEntity() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
