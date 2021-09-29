package com.example.softwareengineeringbackend.service;

import com.example.softwareengineeringbackend.dao.UserDao;
import com.example.softwareengineeringbackend.domain.User;
import com.example.softwareengineeringbackend.domain.exception.UserNotFoundException;
import com.example.softwareengineeringbackend.domain.response.UserResponse;
import com.example.softwareengineeringbackend.jwt.JwtService;
import com.example.softwareengineeringbackend.model.UserResourceBuilder;
import com.example.softwareengineeringbackend.rest.resources.UserResource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Random;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class LoginServiceTest {
    @Mock
    private UserDao userDao;

    @Mock
    private JwtService jwtService;

    @InjectMocks
    private LoginService loginService;
    private UserResource userResource;

    @Captor
    private ArgumentCaptor<User> userCaptor;

    @BeforeEach
    void init() {
        userResource = UserResourceBuilder.anUserResource().build();
    }

    @Test
    void throwsUserNotFoundExceptionWhenUserNotFound() {
        when(userDao.findUserByEmailAndPassword(anyString(), anyString())).thenReturn(null);

        Assertions.assertThrows(UserNotFoundException.class, () -> loginService.login(userResource));
    }

    @Test
    void responseTokenNotNullAndStatusOkWhenUserFound() {
        User foundUser = new User();
        foundUser.setId(999L);
        foundUser.setEmail(userResource.getEmail());
        foundUser.setPassword(userResource.getPassword());

        when(userDao.findUserByEmailAndPassword(anyString(), anyString())).thenReturn(foundUser);
        when(jwtService.generateToken(anyLong(), anyString(), isNull())).thenReturn("eyJhbGciOiJIUzI1NiJ9.eyJlbWFpbCI6InRvbS50cnV5ZW5AZ21haWwuY29tIiwiaWQiOjEsImV4cGlyYXRpb24iOjE2MjI3MjAzNTAxNTIsImF1dGhEYXRlIjoxNjIyNjMzOTUwMTUyfQ.gREAqVQUYj8EnGqeL7e5j7OjQ7UD-3J3w3qXijUNl3w");

        UserResponse userResponse = loginService.login(userResource);

        Assertions.assertNotNull(userResponse);
        Assertions.assertNotNull(userResponse.getToken());
        Assertions.assertEquals(200, userResponse.getStatus());
        Assertions.assertEquals("/login", userResponse.getPath());
    }
}
