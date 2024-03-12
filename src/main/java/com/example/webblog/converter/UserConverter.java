package com.example.webblog.converter;

import com.example.webblog.entity.UserEntity;
import com.example.webblog.entity.UserRoleEntity;
import com.example.webblog.model.UserRequestModel;
import com.example.webblog.model.UserResponseModel;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserConverter {

    public UserResponseModel toUserModel(UserEntity entity){
        UserResponseModel model = new UserResponseModel();
        model.setId(entity.getId());
        model.setFullName(entity.getFullName());
        model.setUsername(entity.getUsername());
        model.setEmail(entity.getEmail());
        model.setAvatar(entity.getAvatar());
        model.setStatus(entity.getStatus().getName());
        model.setDeleted(entity.isDeleted());
        model.setCreatedDate(entity.getCreatedDate());
        model.setLastModifiedDate(entity.getLastModifiedDate());

        return model;
    }

    public UserResponseModel toUserResponseModel(UserEntity entity, List<UserRoleEntity> userRoleEntities){
        UserResponseModel model = toUserModel(entity);

        List<String> roles = new ArrayList<>();
        userRoleEntities.forEach((item) -> {
            roles.add(item.getRole().getName());
        });

        model.setRole(roles);

        return model;
    }

    public UserEntity toEntity(UserRequestModel model){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String password = passwordEncoder.encode(model.getPassword());

        UserEntity entity = new UserEntity();
        entity.setFullName(model.getFullName());
        entity.setUsername(model.getUsername());
        entity.setEmail(model.getEmail());
        entity.setPassword(password);

        return entity;
    }

    public UserEntity toEntity(UserRequestModel model, UserEntity entity){
        if(model.getFullName() != null){
            entity.setFullName(model.getFullName());
        }
        if(model.getPassword() != null){
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String password = passwordEncoder.encode(model.getPassword());

            entity.setPassword(password);
        }

        return entity;
    }
}
