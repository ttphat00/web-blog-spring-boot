package com.example.webblog.service;

import com.example.webblog.model.PaginationModel;
import com.example.webblog.model.UserRequestModel;
import com.example.webblog.model.UserResponseModel;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface UserService {
    List<UserResponseModel> getAllUsers();
    PaginationModel getAllUsers(int page, int limit);
    UserResponseModel register(UserRequestModel model);
    UserResponseModel updateInfo(int id, UserRequestModel model);
    UserResponseModel deleteUser(int id);
    UserResponseModel updateAvatar(int id, MultipartFile file);
    Resource getAvatar(String filename);
    int totalItem();
}
