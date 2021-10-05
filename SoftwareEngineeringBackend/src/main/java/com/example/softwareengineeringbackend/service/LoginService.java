package com.example.softwareengineeringbackend.service;

import com.example.softwareengineeringbackend.dao.UserDao;
import com.example.softwareengineeringbackend.domain.User;
import com.example.softwareengineeringbackend.domain.builder.UserResponseBuilder;
import com.example.softwareengineeringbackend.domain.exception.UserNotFoundException;
import com.example.softwareengineeringbackend.domain.response.Response;
import com.example.softwareengineeringbackend.jwt.JwtService;
import com.example.softwareengineeringbackend.rest.resources.UserResource;
import com.example.softwareengineeringbackend.utils.Utils;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
    private final JwtService jwtService;

    private final UserDao userDao;

    public LoginService(JwtService jwtService, UserDao userDao) {
        this.jwtService = jwtService;
        this.userDao = userDao;
    }

    public Response login(UserResource userResource) {
        String password = Utils.hashPassword(userResource.getPassword());

        User user = userDao.findUserByEmailAndPassword(userResource.getEmail(), password);

        if (user == null) throw new UserNotFoundException("Invalid email/password combination");

        String token = jwtService.generateToken(user.getId(), user.getEmail(), null);

        return new UserResponseBuilder().withPath("/login").withToken(token).withOk().build();
    }
}
