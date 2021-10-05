package com.example.softwareengineeringbackend.service;

import com.example.softwareengineeringbackend.dao.UserDao;
import com.example.softwareengineeringbackend.domain.User;
import com.example.softwareengineeringbackend.domain.exception.UserAlreadyExistsException;
import com.example.softwareengineeringbackend.domain.response.Response;
import com.example.softwareengineeringbackend.model.UserResourceBuilder;
import com.example.softwareengineeringbackend.rest.resources.UserResource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Random;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class RegisterServiceTest {
    private final Random random = new Random();

    @Mock
    private UserDao userDao;

    @InjectMocks
    private RegisterService registerService;
    private UserResource userResource;

    @Captor
    private ArgumentCaptor<User> userCaptor;

    @BeforeEach
    void init() { userResource = UserResourceBuilder.anUserResource().build(); }

    @Test
    void throwsUserAlreadyExistsExceptionWhenUserExists() {
        User user = new User();
        user.setEmail(userResource.getEmail());
        user.setPassword(userResource.getPassword());

        when(userDao.findUserByEmail(anyString())).thenReturn(user);

        Assertions.assertThrows(UserAlreadyExistsException.class, () -> registerService.register(userResource));
    }

    @Test
    void responseStatusOkWhenUserRegistered() {
        when(userDao.findUserByEmail(anyString())).thenReturn(null);

        Response response = registerService.register(userResource);

        Assertions.assertNotNull(response);
        Assertions.assertEquals(200, response.getStatus());
        Assertions.assertEquals("/register", response.getPath());

    }
}
