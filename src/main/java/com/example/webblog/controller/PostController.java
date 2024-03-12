package com.example.webblog.controller;

import com.example.webblog.model.PostRequestModel;
import com.example.webblog.response.ResponseHandler;
import com.example.webblog.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/posts")
public class PostController {
    @Autowired
    private PostService postService;

    @PostMapping
    public ResponseEntity<Object> createPost(@ModelAttribute PostRequestModel model){
        return ResponseHandler.responseBuilder(
                "Created post successfully",
                HttpStatus.CREATED,
                postService.createPost(model)
        );
    }

    @GetMapping
    public ResponseEntity<Object> getAllPosts(@RequestParam(value = "page", defaultValue = "1") int page,
                                               @RequestParam(value = "limit", defaultValue = "1") int limit,
                                               @RequestParam(value = "search", required = false) String search){
        return ResponseHandler.responseBuilder(
                "Get all posts pagination",
                HttpStatus.OK,
                postService.getAllPosts(page, limit, search)
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getPostById(@PathVariable("id") int id){
        return ResponseHandler.responseBuilder(
                "Get post by id",
                HttpStatus.OK,
                postService.getPostById(id)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updatePost(@PathVariable("id") int id, @ModelAttribute PostRequestModel model){
        return ResponseHandler.responseBuilder(
                "Updated post successfully",
                HttpStatus.OK,
                postService.updatePost(id, model)
        );
    }

    @PutMapping("/deleting/{id}")
    public ResponseEntity<Object> deletePost(@PathVariable("id") int id){
        return ResponseHandler.responseBuilder(
                "Deleted post successfully",
                HttpStatus.OK,
                postService.deletePost(id)
        );
    }

    @PutMapping("/approving/{id}")
    public ResponseEntity<Object> approvePost(@PathVariable("id") int postId, @RequestBody int statusId){
        return ResponseHandler.responseBuilder(
                "Approved post successfully",
                HttpStatus.OK,
                postService.approvePost(postId, statusId)
        );
    }

    @GetMapping("/thumbnail/{filename:.+}")
    public ResponseEntity<Resource> getThumbnail(@PathVariable String filename){
        Resource file = postService.getThumbnail(filename);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" + file.getFilename() + "\"")
                .body(file);
    }
}
