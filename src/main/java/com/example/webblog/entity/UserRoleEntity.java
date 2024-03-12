package com.example.webblog.entity;

import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Entity
@Table(name = "user_role")
@EntityListeners(AuditingEntityListener.class)
public class UserRoleEntity extends BaseEntity {
    @ManyToOne
    private UserEntity user;
    @ManyToOne
    private RoleEntity role;
    @LastModifiedBy
    @ManyToOne
    @JoinColumn(name = "last_modified_by")
    private UserEntity lastModifiedBy;
    @ManyToOne
    @JoinColumn(name = "deleted_by")
    private UserEntity deletedBy;

    public UserRoleEntity() {
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public RoleEntity getRole() {
        return role;
    }

    public void setRole(RoleEntity role) {
        this.role = role;
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
