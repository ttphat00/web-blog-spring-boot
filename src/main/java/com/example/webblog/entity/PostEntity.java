package com.example.webblog.entity;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Entity
@Table(name = "post")
@EntityListeners(AuditingEntityListener.class)
public class PostEntity extends BaseEntity {
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String description;
    @Column(nullable = false)
    private String thumbnail;
    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;
    @CreatedBy
    @ManyToOne
    @JoinColumn(name = "created_by")
    private UserEntity createdBy;
    @LastModifiedBy
    @ManyToOne
    @JoinColumn(name = "last_modified_by")
    private UserEntity lastModifiedBy;
    @ManyToOne
    @JoinColumn(name = "deleted_by")
    private UserEntity deletedBy;
    @ManyToOne
    @JoinColumn(name = "approved_by")
    private UserEntity approvedBy;
    @ManyToOne
    private TopicEntity topic;
    @ManyToOne
    private PostStatusEntity status;

    public PostEntity() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
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

    public UserEntity getApprovedBy() {
        return approvedBy;
    }

    public void setApprovedBy(UserEntity approvedBy) {
        this.approvedBy = approvedBy;
    }

    public TopicEntity getTopic() {
        return topic;
    }

    public void setTopic(TopicEntity topic) {
        this.topic = topic;
    }

    public PostStatusEntity getStatus() {
        return status;
    }

    public void setStatus(PostStatusEntity status) {
        this.status = status;
    }
}
