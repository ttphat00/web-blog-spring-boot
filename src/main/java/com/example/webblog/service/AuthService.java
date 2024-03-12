package com.example.webblog.service;

import com.example.webblog.model.AuthRequestModel;
import com.example.webblog.model.AuthResponseModel;

public interface AuthService {
    AuthResponseModel login(AuthRequestModel requestModel);
}
