package com.example.webblog.entity;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Entity
@Table(name = "comment")
@EntityListeners(AuditingEntityListener.class)
public class CommentEntity extends BaseEntity {
    @Column(nullable = false)
    private String content;
    @CreatedBy
    @ManyToOne
    @JoinColumn(name = "created_by")
    private UserEntity createdBy;
    @ManyToOne
    private PostEntity post;
    @LastModifiedBy
    @ManyToOne
    @JoinColumn(name = "last_modified_by")
    private UserEntity lastModifiedBy;
    @ManyToOne
    @JoinColumn(name = "deleted_by")
    private UserEntity deletedBy;

    public CommentEntity() {
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public UserEntity getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(UserEntity createdBy) {
        this.createdBy = createdBy;
    }

    public PostEntity getPost() {
        return post;
    }

    public void setPost(PostEntity post) {
        this.post = post;
    }

    public UserEntity getLastModifiedBy() {
        return lastModifiedBy;
    }

    public void setLastModifiedBy(UserEntity lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    public UserEntity getDeletedBy() {
        return deletedBy;
    }

    public void setDeletedBy(UserEntity deletedBy) {
        this.deletedBy = deletedBy;
    }
}
