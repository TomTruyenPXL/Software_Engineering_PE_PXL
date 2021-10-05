package com.example.softwareengineeringbackend.rest;

import com.example.softwareengineeringbackend.domain.response.Response;
import com.example.softwareengineeringbackend.rest.resources.UserResource;
import com.example.softwareengineeringbackend.service.RegisterService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "register", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
public class RegisterController {
    private final RegisterService registerService;

    public RegisterController(RegisterService registerService) { this.registerService = registerService; }

    @PostMapping
    public ResponseEntity<Response> register(@RequestBody @Valid UserResource user) {
        Response response = registerService.register(user);

        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getStatus()));
    }
}
