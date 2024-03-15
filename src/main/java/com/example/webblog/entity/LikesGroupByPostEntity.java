package com.example.webblog.entity;

public class LikesGroupByPostEntity {
    private PostEntity post;
    private Long likeCount;

    public LikesGroupByPostEntity(PostEntity post, Long likeCount) {
        this.post = post;
        this.likeCount = likeCount;
    }

    public PostEntity getPost() {
        return post;
    }

    public void setPost(PostEntity post) {
        this.post = post;
    }

    public Long getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(Long likeCount) {
        this.likeCount = likeCount;
    }
}
