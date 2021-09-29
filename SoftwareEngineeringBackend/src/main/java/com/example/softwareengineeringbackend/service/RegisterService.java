package com.example.softwareengineeringbackend.service;

import com.example.softwareengineeringbackend.dao.UserDao;
import com.example.softwareengineeringbackend.domain.User;
import com.example.softwareengineeringbackend.domain.builder.UserResponseBuilder;
import com.example.softwareengineeringbackend.domain.exception.UserAlreadyExistsException;
import com.example.softwareengineeringbackend.domain.response.UserResponse;
import com.example.softwareengineeringbackend.rest.resources.UserResource;
import com.example.softwareengineeringbackend.utils.Utils;
import org.springframework.stereotype.Service;

@Service
public class RegisterService {
    private final UserDao userDao;

    public RegisterService(UserDao userDao) {this.userDao = userDao;}

    public UserResponse register(UserResource userResource) {
        User user = userDao.findUserByEmail(userResource.getEmail());

        if(user != null) { throw new UserAlreadyExistsException("Email is already being used"); }

        user = new User();
        user.setEmail(userResource.getEmail());
        user.setPassword(Utils.hashPassword(userResource.getPassword()));

        userDao.save(user);

        return new UserResponseBuilder().withPath("/register").withMessage("Registration complete").withOk().build();
    }
}
