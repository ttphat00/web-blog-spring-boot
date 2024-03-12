package com.example.webblog.service.impl;

import com.example.webblog.constant.Role;
import com.example.webblog.converter.UserConverter;
import com.example.webblog.entity.RoleEntity;
import com.example.webblog.entity.UserEntity;
import com.example.webblog.entity.UserRoleEntity;
import com.example.webblog.model.UserResponseModel;
import com.example.webblog.repository.RoleRepository;
import com.example.webblog.repository.UserRoleRepository;
import com.example.webblog.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserRoleServiceImpl implements UserRoleService {
    @Autowired
    private UserRoleRepository userRoleRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private UserConverter userConverter;

    @Override
    public UserResponseModel getRolesByUser(UserEntity user) {
        List<UserRoleEntity> entities = userRoleRepository.findByUser(user);
        UserResponseModel model = userConverter.toUserResponseModel(user, entities);
        return model;
    }

    @Override
    public UserResponseModel createDefaultRole(UserEntity user) {
        List<UserRoleEntity> userRoles = new ArrayList<>();
        RoleEntity role = roleRepository.findByName(Role.NORMAL_USER.getName()).get();

        UserRoleEntity newUserRole = new UserRoleEntity();
        newUserRole.setUser(user);
        newUserRole.setRole(role);
        UserRoleEntity savedUserRole = userRoleRepository.save(newUserRole);

        userRoles.add(savedUserRole);

        UserResponseModel model = userConverter.toUserResponseModel(user, userRoles);
        return model;
    }
}
