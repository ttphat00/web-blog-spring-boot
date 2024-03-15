package com.example.webblog.model;

import java.util.ArrayList;
import java.util.List;

public class LikesResponseModel {
    private PostResponseModel post;
    private int totalLikes;
    private List<Object> likedBy = new ArrayList<>();

    public LikesResponseModel() {
    }

    public PostResponseModel getPost() {
        return post;
    }

    public void setPost(PostResponseModel post) {
        this.post = post;
    }

    public int getTotalLikes() {
        return totalLikes;
    }

    public void setTotalLikes(int totalLikes) {
        this.totalLikes = totalLikes;
    }

    public List<Object> getLikedBy() {
        return likedBy;
    }

    public void setLikedBy(List<Object> likedBy) {
        this.likedBy = likedBy;
    }
}
