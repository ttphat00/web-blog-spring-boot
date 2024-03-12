package com.example.webblog.controller;

import com.example.webblog.model.TopicRequestModel;
import com.example.webblog.response.ResponseHandler;
import com.example.webblog.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/topics")
public class TopicController {
    @Autowired
    private TopicService topicService;

    @PostMapping
    public ResponseEntity<Object> createTopic(@RequestBody TopicRequestModel model){
        return ResponseHandler.responseBuilder(
                "Created topic successfully",
                HttpStatus.CREATED,
                topicService.createTopic(model)
        );
    }

    @GetMapping
    public ResponseEntity<Object> getAllTopics(){
        return ResponseHandler.responseBuilder(
                "Get all topics",
                HttpStatus.OK,
                topicService.getAllTopics()
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateTopic(@PathVariable("id") int id, @RequestBody TopicRequestModel model){
        return ResponseHandler.responseBuilder(
                "Updated topic successfully",
                HttpStatus.OK,
                topicService.updateTopic(id, model)
        );
    }

    @PutMapping("/deleting/{id}")
    public ResponseEntity<Object> deleteTopic(@PathVariable("id") int id){
        return ResponseHandler.responseBuilder(
                "Deleted topic successfully",
                HttpStatus.OK,
                topicService.deleteTopic(id)
        );
    }
}
