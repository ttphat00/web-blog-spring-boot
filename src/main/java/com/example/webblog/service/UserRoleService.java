package com.example.webblog.service;

import com.example.webblog.entity.UserEntity;
import com.example.webblog.model.UserResponseModel;

public interface UserRoleService {
    UserResponseModel getRolesByUser(UserEntity user);
    UserResponseModel createDefaultRole(UserEntity user);
}
