package com.example.webblog.repository;

import com.example.webblog.entity.UserEntity;
import com.example.webblog.entity.UserRoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRoleRepository extends JpaRepository<UserRoleEntity, Integer> {
    List<UserRoleEntity> findByUser(UserEntity user);
}
