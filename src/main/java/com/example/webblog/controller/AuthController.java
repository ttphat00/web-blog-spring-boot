package com.example.webblog.controller;

import com.example.webblog.model.AuthRequestModel;
import com.example.webblog.response.ResponseHandler;
import com.example.webblog.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody AuthRequestModel request) {
        try {
            return ResponseHandler.responseBuilder(
                    "Login successfully",
                    HttpStatus.OK,
                    authService.login(request)
            );

        } catch (BadCredentialsException ex) {
            return ResponseHandler.responseBuilder(
                    ex.getMessage(),
                    HttpStatus.UNAUTHORIZED,
                    null
            );
        }
    }
}
