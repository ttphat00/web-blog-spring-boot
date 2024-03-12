package com.example.webblog.entity;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Entity
@Table(name = "topic")
@EntityListeners(AuditingEntityListener.class)
public class TopicEntity extends BaseEntity {
    @Column(nullable = false, unique = true)
    private String name;
    @ManyToOne
    @JoinColumn(name = "deleted_by")
    private UserEntity deletedBy;
    @CreatedBy
    @ManyToOne
    @JoinColumn(name = "created_by")
    private UserEntity createdBy;
    @LastModifiedBy
    @ManyToOne
    @JoinColumn(name = "last_modified_by")
    private UserEntity lastModifiedBy;

    public TopicEntity() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UserEntity getDeletedBy() {
        return deletedBy;
    }

    public void setDeletedBy(UserEntity deletedBy) {
        this.deletedBy = deletedBy;
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
}
