package com.example.webblog.controller;

import com.example.webblog.model.UserRequestModel;
import com.example.webblog.response.ResponseHandler;
import com.example.webblog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<Object> register(@RequestBody UserRequestModel model) {
        return ResponseHandler.responseBuilder(
                "Register successfully",
                HttpStatus.CREATED,
                userService.register(model)
        );
    }

    @GetMapping
    public ResponseEntity<Object> getAllUsers(@RequestParam(value = "page", required = false) Integer page,
                                              @RequestParam(value = "limit", required = false) Integer limit) {
        if(page != null && limit != null){
            return ResponseHandler.responseBuilder(
                    "Get all users pagination",
                    HttpStatus.OK,
                    userService.getAllUsers(page, limit)
            );
        }else {
            return ResponseHandler.responseBuilder(
                    "Get all users",
                    HttpStatus.OK,
                    userService.getAllUsers()
            );
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateInfo(@PathVariable("id") int id, @RequestBody UserRequestModel model){
        return ResponseHandler.responseBuilder(
                "Updated user info successfully",
                HttpStatus.OK,
                userService.updateInfo(id, model)
        );
    }

    @PutMapping("/deleting/{id}")
    public ResponseEntity<Object> deleteUser(@PathVariable("id") int id){
        return ResponseHandler.responseBuilder(
                "Deleted user successfully",
                HttpStatus.OK,
                userService.deleteUser(id)
        );
    }

    @PutMapping("/avatar/{id}")
    public ResponseEntity<Object> updateAvatar(@PathVariable("id") int id, @RequestParam("file") MultipartFile file){
        return ResponseHandler.responseBuilder(
                "Updated user avatar successfully",
                HttpStatus.OK,
                userService.updateAvatar(id, file)
        );
    }

    @GetMapping("/avatar/{filename:.+}")
    public ResponseEntity<Resource> getAvatar(@PathVariable String filename){
        Resource file = userService.getAvatar(filename);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" + file.getFilename() + "\"")
                .body(file);
    }
}
