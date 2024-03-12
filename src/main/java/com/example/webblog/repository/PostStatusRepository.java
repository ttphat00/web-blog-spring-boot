package com.example.webblog.repository;

import com.example.webblog.entity.PostStatusEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PostStatusRepository extends JpaRepository<PostStatusEntity, Integer> {
    Optional<PostStatusEntity> findByName(String name);
}
