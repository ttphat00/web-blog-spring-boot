package com.example.webblog.repository;

import com.example.webblog.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.transaction.annotation.Propagation;
//import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    //@Transactional(propagation = Propagation.REQUIRES_NEW)
    Optional<UserEntity> findByUsername(String username);
    Optional<UserEntity> findByEmail(String email);
}
