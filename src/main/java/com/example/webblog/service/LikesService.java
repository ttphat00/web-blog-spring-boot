package com.example.webblog.service;

import com.example.webblog.model.LikesResponseModel;

import java.util.List;

public interface LikesService {
    List<LikesResponseModel> getLikesAllPosts();
    LikesResponseModel getLikesByPost(int postId);
    void likePost(int postId);
    void deleteLikedPost(int postId);
}
