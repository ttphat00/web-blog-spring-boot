package com.example.webblog.service;

import com.example.webblog.model.PaginationModel;
import com.example.webblog.model.PostRequestModel;
import com.example.webblog.model.PostResponseModel;
import org.springframework.core.io.Resource;

public interface PostService {
    PaginationModel getAllPosts(int page, int limit, String search);
    PostResponseModel getPostById(int id);
    PostResponseModel createPost(PostRequestModel model);
    PostResponseModel updatePost(int id, PostRequestModel model);
    PostResponseModel deletePost(int id);
    PostResponseModel approvePost(int postId, int statusId);
    Resource getThumbnail(String filename);
    int totalItem();
    int totalSearchingItem(String search);
}
