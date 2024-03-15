package com.example.webblog.controller;

import com.example.webblog.response.ResponseHandler;
import com.example.webblog.service.LikesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/likes")
public class LikesController {
    @Autowired
    private LikesService likesService;

    @PostMapping
    public ResponseEntity<Object> likePost(@RequestBody int postId){
        likesService.likePost(postId);
        return ResponseHandler.responseBuilder(
                "Liked post successfully",
                HttpStatus.CREATED,
                null
        );
    }

    @DeleteMapping
    public ResponseEntity<Object> deleteLikedPost(@RequestBody int postId){
        likesService.deleteLikedPost(postId);
        return ResponseHandler.responseBuilder(
                "Unlike post successfully",
                HttpStatus.OK,
                null
        );
    }

    @GetMapping
    public ResponseEntity<Object> getLikesByPost(@RequestBody int postId){
        return ResponseHandler.responseBuilder(
                "get likes of a post",
                HttpStatus.OK,
                likesService.getLikesByPost(postId)
        );
    }

    @GetMapping("/all")
    public ResponseEntity<Object> getLikesAllPosts(){
        return ResponseHandler.responseBuilder(
                "get likes of all posts",
                HttpStatus.OK,
                likesService.getLikesAllPosts()
        );
    }
}
