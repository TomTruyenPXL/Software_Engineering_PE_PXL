package com.example.softwareengineeringbackend.rest;

import com.example.softwareengineeringbackend.domain.response.UserResponse;
import com.example.softwareengineeringbackend.rest.resources.UserResource;
import com.example.softwareengineeringbackend.service.LoginService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "login", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
public class LoginController {
    private final LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping
    public ResponseEntity<UserResponse> login(@RequestBody @Valid UserResource user) {
        UserResponse response = loginService.login(user);

        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getStatus()));
    }
}
