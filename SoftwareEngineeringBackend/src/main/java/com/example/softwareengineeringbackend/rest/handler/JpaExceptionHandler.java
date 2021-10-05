package com.example.softwareengineeringbackend.rest.handler;

import com.example.softwareengineeringbackend.domain.builder.UserResponseBuilder;
import com.example.softwareengineeringbackend.domain.response.Response;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Objects;

@RestControllerAdvice
public class JpaExceptionHandler {
    @ExceptionHandler(value = BindException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Response jpaValidException(BindException e) {
        String error = "";

        if(e.hasErrors()) {
            try {
                if(e.getFieldError("email") != null) {
                    error = Objects.requireNonNull(e.getFieldError("email")).getDefaultMessage();
                } else if (e.getFieldError("password") != null) {
                    error = Objects.requireNonNull(e.getFieldError("password")).getDefaultMessage();
                }
            } catch (NullPointerException ex) {
                System.out.println("NullPointerException JpaExceptionHandler: " + ex.getMessage());
            }
        }

        return new UserResponseBuilder().withPath("/error").withMessage(error).withBadRequest().build();
    }
}
