package com.example.webblog.repository;

import com.example.webblog.entity.PostEntity;
import com.example.webblog.entity.SavedPostEntity;
import com.example.webblog.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface SavedPostRepository extends JpaRepository<SavedPostEntity, Integer> {
    List<SavedPostEntity> findByUser(UserEntity user);

    @Query("SELECT p FROM SavedPostEntity p WHERE p.user = :user AND p.post = :post")
    Optional<SavedPostEntity> findByUserAndPost(UserEntity user, PostEntity post);
}
