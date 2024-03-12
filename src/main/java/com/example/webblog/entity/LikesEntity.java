package com.example.webblog.entity;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "likes")
@EntityListeners(AuditingEntityListener.class)
public class LikesEntity extends BaseEntity {
    @CreatedBy
    @ManyToOne
    private UserEntity user;
    @ManyToOne
    private PostEntity post;

    public LikesEntity() {
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public PostEntity getPost() {
        return post;
    }

    public void setPost(PostEntity post) {
        this.post = post;
    }
}
