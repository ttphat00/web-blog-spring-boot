package com.example.webblog.service.impl;

import com.example.webblog.constant.UserStatus;
import com.example.webblog.converter.UserConverter;
import com.example.webblog.entity.UserEntity;
import com.example.webblog.entity.UserStatusEntity;
import com.example.webblog.exception.NotFoundException;
import com.example.webblog.model.PaginationModel;
import com.example.webblog.model.UserRequestModel;
import com.example.webblog.model.UserResponseModel;
import com.example.webblog.repository.UserRepository;
import com.example.webblog.repository.UserStatusRepository;
import com.example.webblog.service.FilesStorageService;
import com.example.webblog.service.UserRoleService;
import com.example.webblog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserStatusRepository userStatusRepository;
    @Autowired
    private UserRoleService userRoleService;
    @Autowired
    @Qualifier("avatarStorageService")
    private FilesStorageService filesStorageService;
    @Autowired
    private UserConverter userConverter;

    @Override
    public List<UserResponseModel> getAllUsers() {
        List<UserEntity> entities = userRepository.findAll();
        List<UserResponseModel> result = new ArrayList<>();
        entities.forEach((entity) -> {
            UserResponseModel model = userRoleService.getRolesByUser(entity);
            result.add(model);
        });
        return result;
    }

    @Override
    public PaginationModel getAllUsers(int page, int limit) {
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        Pageable pageable = PageRequest.of(page - 1, limit, sort);

        List<UserEntity> entities = userRepository.findAll(pageable).getContent();
        List<UserResponseModel> result = new ArrayList<>();
        entities.forEach((entity) -> {
            UserResponseModel model = userRoleService.getRolesByUser(entity);
            result.add(model);
        });

        int totalPage = (int) Math.ceil((double) totalItem() / limit);
        PaginationModel paginationModel = new PaginationModel();
        paginationModel.setPage(page);
        paginationModel.setTotalPage(totalPage);
        paginationModel.setResults(result);

        return paginationModel;
    }

    @Override
    public UserResponseModel register(UserRequestModel model) {
        UserStatusEntity userStatus = userStatusRepository.findByName(UserStatus.ACTIVE.getName()).get();
        UserEntity newUser = userConverter.toEntity(model);
        newUser.setStatus(userStatus);
        UserEntity savedUser = userRepository.save(newUser);

        return userRoleService.createDefaultRole(savedUser);
    }

    @Override
    public UserResponseModel updateInfo(int id, UserRequestModel model) {
        UserEntity oldUser = userRepository.findById(id).orElseThrow(
                () -> new NotFoundException("User does not exist")
        );

        UserEntity updatedUser = userConverter.toEntity(model, oldUser);
        updatedUser = userRepository.save(updatedUser);

        return userRoleService.getRolesByUser(updatedUser);
    }

    @Override
    public UserResponseModel deleteUser(int id) {
        UserEntity user = userRepository.findById(id).orElseThrow(
                () -> new NotFoundException("User does not exist")
        );

        UserStatusEntity userStatus = userStatusRepository.findByName(UserStatus.DELETED.getName()).get();

        user.setDeleted(true);
        user.setStatus(userStatus);
        user = userRepository.save(user);

        return userRoleService.getRolesByUser(user);
    }

    @Override
    public UserResponseModel updateAvatar(int id, MultipartFile file) {
        UserEntity user = userRepository.findById(id).orElseThrow(
                () -> new NotFoundException("User does not exist")
        );

        filesStorageService.save(file);
        String url = filesStorageService.buildFileUrl(file);

        user.setAvatar(url);
        user = userRepository.save(user);

        return userRoleService.getRolesByUser(user);
    }

    @Override
    public Resource getAvatar(String filename) {
        return filesStorageService.load(filename);
    }

    @Override
    public int totalItem() {
        return (int) userRepository.count();
    }
}
