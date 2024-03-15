package com.example.webblog.controller;

import com.example.webblog.response.ResponseHandler;
import com.example.webblog.service.SavedPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/saved-posts")
public class SavedPostController {
    @Autowired
    private SavedPostService savedPostService;

    @PostMapping
    public ResponseEntity<Object> savePost(@RequestBody int postId){
        savedPostService.savePost(postId);
        return ResponseHandler.responseBuilder(
                "Saved post successfully",
                HttpStatus.CREATED,
                null
        );
    }

    @GetMapping
    public ResponseEntity<Object> getAllSavedPosts(){
        return ResponseHandler.responseBuilder(
                "Get all saved post",
                HttpStatus.OK,
                savedPostService.getAllSavedPosts()
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteSavedPost(@PathVariable("id") int id){
        savedPostService.deleteSavedPost(id);
        return ResponseHandler.responseBuilder(
                "Deleted saved post successfully",
                HttpStatus.OK,
                null
        );
    }
}
