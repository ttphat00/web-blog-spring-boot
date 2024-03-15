package com.example.webblog.repository;

import com.example.webblog.entity.LikesEntity;
import com.example.webblog.entity.LikesGroupByPostEntity;
import com.example.webblog.entity.PostEntity;
import com.example.webblog.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface LikesRepository extends JpaRepository<LikesEntity, Integer> {
    @Query("SELECT l FROM LikesEntity l WHERE l.user = :user AND l.post = :post")
    Optional<LikesEntity> findByUserAndPost(UserEntity user, PostEntity post);

    @Transactional
    @Modifying
    @Query("DELETE FROM LikesEntity l WHERE l.user = ?1 AND l.post = ?2")
    void deleteByUserAndPost(UserEntity user, PostEntity post);

//    @Transactional
//    void deleteByUserAndPost(UserEntity user, PostEntity post);

    List<LikesEntity> findByPost(PostEntity post);

    @Query("SELECT new com.example.webblog.entity.LikesGroupByPostEntity(l.post, COUNT(l)) " +
            "FROM LikesEntity l GROUP BY l.post")
    List<LikesGroupByPostEntity> countLikesByPost();
}
