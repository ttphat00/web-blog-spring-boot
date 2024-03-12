package com.example.webblog.service.impl;

import com.example.webblog.entity.UserEntity;
import com.example.webblog.model.AuthRequestModel;
import com.example.webblog.model.AuthResponseModel;
import com.example.webblog.model.TokenModel;
import com.example.webblog.service.AuthService;
import com.example.webblog.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {
    @Autowired
    AuthenticationManager authManager;
    @Autowired
    JwtTokenUtil jwtUtil;

    @Override
    public AuthResponseModel login(AuthRequestModel requestModel) {
        Authentication authentication = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        requestModel.getUsername(), requestModel.getPassword())
        );

        UserEntity user = (UserEntity) authentication.getPrincipal();

        String accessToken = jwtUtil.generateAccessToken(user);
        String refreshToken = jwtUtil.generateRefreshToken();

        TokenModel token = new TokenModel();
        token.setAccessToken(accessToken);
        token.setRefreshToken(refreshToken);

        AuthResponseModel response = new AuthResponseModel();
        response.setFullName(user.getFullName());
        response.setUsername(user.getUsername());
        response.setEmail(user.getEmail());
        response.setAvatar(user.getAvatar());
        response.setCreatedDate(user.getCreatedDate());
        response.setToken(token);

        return response;
    }
}
