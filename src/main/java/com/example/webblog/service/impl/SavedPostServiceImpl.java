package com.example.webblog.service.impl;

import com.example.webblog.converter.PostConverter;
import com.example.webblog.entity.PostEntity;
import com.example.webblog.entity.SavedPostEntity;
import com.example.webblog.entity.UserEntity;
import com.example.webblog.exception.NotFoundException;
import com.example.webblog.model.PostResponseModel;
import com.example.webblog.repository.PostRepository;
import com.example.webblog.repository.SavedPostRepository;
import com.example.webblog.service.SavedPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SavedPostServiceImpl implements SavedPostService {
    @Autowired
    private SavedPostRepository savedPostRepository;
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private AuditorAware<UserEntity> auditorAware;
    @Autowired
    private PostConverter postConverter;

    @Override
    public List<PostResponseModel> getAllSavedPosts() {
        List<SavedPostEntity> savedPosts = savedPostRepository.findByUser(auditorAware.getCurrentAuditor().get());
        List<PostResponseModel> models = new ArrayList<>();

        savedPosts.forEach(savedPost -> {
            PostResponseModel model = postConverter.toResponseModel(savedPost.getPost());
            models.add(model);
        });

        return models;
    }

    @Override
    public void savePost(int postId) {
        SavedPostEntity newSavedPost = new SavedPostEntity();

        PostEntity post = postRepository.findById(postId).orElseThrow(
                () -> new NotFoundException("Post does not exist")
        );

        if(savedPostRepository.findByUserAndPost(auditorAware.getCurrentAuditor().get(), post).isPresent()){
            throw new RuntimeException("Post has already saved");
        }

        newSavedPost.setPost(post);

        savedPostRepository.save(newSavedPost);
    }

    @Override
    public void deleteSavedPost(int id) {
        savedPostRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Saved post does not exist")
        );

        savedPostRepository.deleteById(id);
    }
}
