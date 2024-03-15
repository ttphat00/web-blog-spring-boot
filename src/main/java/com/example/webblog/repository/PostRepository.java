package com.example.webblog.repository;

import com.example.webblog.entity.PostEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostRepository extends JpaRepository<PostEntity, Integer> {
    @Query("SELECT p FROM PostEntity p " +
            "WHERE LOWER(p.title) LIKE LOWER(concat('%', :search, '%')) " +
            "OR LOWER(p.content) LIKE LOWER(concat('%', :search, '%')) " +
            "OR LOWER(p.description) LIKE LOWER(concat('%', :search, '%'))")
    List<PostEntity> findByTitleOrDescriptionOrContentContaining(String search, Pageable pageable);

    @Query("SELECT COUNT(p) FROM PostEntity p " +
            "WHERE LOWER(p.title) LIKE LOWER(concat('%', :search, '%')) " +
            "OR LOWER(p.content) LIKE LOWER(concat('%', :search, '%')) " +
            "OR LOWER(p.description) LIKE LOWER(concat('%', :search, '%'))")
    long countSearchingItem(String search);
}
