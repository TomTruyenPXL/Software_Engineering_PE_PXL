package com.example.softwareengineeringbackend.rest;

import com.example.softwareengineeringbackend.dao.UserDao;
import com.example.softwareengineeringbackend.domain.User;
import com.example.softwareengineeringbackend.domain.builder.UserResponseBuilder;
import com.example.softwareengineeringbackend.service.LoginService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(value = LoginController.class)
public class LoginControllerTest {
    @Autowired
    private MockMvc mockMvc;

    // Required to fix ApplicationContext error
    @MockBean
    private LoginService loginService;

    @MockBean
    private UserDao userDao;

    @Test
    void statusBadRequestWhenEmailNotGiven() throws Exception {
        String body = "{\"email\": \"\", \"password\": \"password\"}";

        mockMvc.perform(post("/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(body)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void statusBadRequestWhenEmailInvalid() throws Exception {
        String body = "{\"email\": \"email\", \"password\": \"password\"}";

        mockMvc.perform(post("/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(body)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void statusOkWhenResourceValid() throws Exception {
        String body = "{\"email\": \"test@test.com\", \"password\": \"Password1\"}";

        User user = new User();
        user.setId(999L);
        user.setEmail("test@test.com");
        user.setPassword("Password1");

        when(userDao.findUserByEmailAndPassword(anyString(), anyString())).thenReturn(user);
        when(loginService.login(any())).thenReturn(new UserResponseBuilder().withOk().build());

        mockMvc.perform(post("/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(body)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
