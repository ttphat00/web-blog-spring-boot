package com.example.webblog.service;

import com.example.webblog.model.PostResponseModel;

import java.util.List;

public interface SavedPostService {
    List<PostResponseModel> getAllSavedPosts();
    void savePost(int postId);
    void deleteSavedPost(int id);
}
