package com.example.webblog.service.impl;

import com.example.webblog.converter.PostConverter;
import com.example.webblog.entity.LikesEntity;
import com.example.webblog.entity.LikesGroupByPostEntity;
import com.example.webblog.entity.PostEntity;
import com.example.webblog.entity.UserEntity;
import com.example.webblog.exception.NotFoundException;
import com.example.webblog.model.LikesResponseModel;
import com.example.webblog.repository.LikesRepository;
import com.example.webblog.repository.PostRepository;
import com.example.webblog.service.LikesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class LikesServiceImpl implements LikesService {
    @Autowired
    private LikesRepository likesRepository;
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private AuditorAware<UserEntity> auditorAware;
    @Autowired
    private PostConverter postConverter;

    @Override
    public void likePost(int postId) {
        LikesEntity newEntity = new LikesEntity();

        PostEntity post = postRepository.findById(postId).orElseThrow(
                () -> new NotFoundException("Post does not exist")
        );

        if(likesRepository.findByUserAndPost(auditorAware.getCurrentAuditor().get(), post).isPresent()){
            throw new RuntimeException("Post has already liked");
        }

        newEntity.setPost(post);

        likesRepository.save(newEntity);
    }

    @Override
    public void deleteLikedPost(int postId) {
        PostEntity post = postRepository.findById(postId).orElseThrow(
                () -> new NotFoundException("Post does not exist")
        );

        if(!likesRepository.findByUserAndPost(auditorAware.getCurrentAuditor().get(), post).isPresent()){
            throw new RuntimeException("Post has not liked yet");
        }

        likesRepository.deleteByUserAndPost(auditorAware.getCurrentAuditor().get(), post);
    }

    @Override
    public List<LikesResponseModel> getLikesAllPosts() {
        List<LikesGroupByPostEntity> entities = likesRepository.countLikesByPost();
        List<LikesResponseModel> models = new ArrayList<>();

        entities.forEach(entity -> {
            LikesResponseModel model = new LikesResponseModel();
            model.setPost(postConverter.toResponseModel(entity.getPost()));
            model.setTotalLikes(Math.toIntExact(entity.getLikeCount()));
            models.add(model);
        });

        return models;
    }

    @Override
    public LikesResponseModel getLikesByPost(int postId) {
        PostEntity post = postRepository.findById(postId).orElseThrow(
                () -> new NotFoundException("Post does not exist")
        );

        List<LikesEntity> entities = likesRepository.findByPost(post);
        List<Object> users = new ArrayList<>();

        entities.forEach(entity -> {
            Map<String, Object> user = new HashMap<>();
            user.put("id", entity.getUser().getId());
            user.put("fullName", entity.getUser().getFullName());
            user.put("username", entity.getUser().getUsername());
            user.put("email", entity.getUser().getEmail());
            user.put("avatar", entity.getUser().getAvatar());
            user.put("deleted", entity.getUser().isDeleted());

            users.add(user);
        });

        LikesResponseModel model = new LikesResponseModel();
        model.setPost(postConverter.toResponseModel(entities.get(0).getPost()));
        model.setTotalLikes(entities.size());
        model.setLikedBy(users);

        return model;
    }
}
