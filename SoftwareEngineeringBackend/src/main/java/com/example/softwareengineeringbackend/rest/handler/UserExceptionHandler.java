package com.example.softwareengineeringbackend.rest.handler;

import com.example.softwareengineeringbackend.domain.builder.UserResponseBuilder;
import com.example.softwareengineeringbackend.domain.exception.UserAlreadyExistsException;
import com.example.softwareengineeringbackend.domain.exception.UserNotFoundException;
import com.example.softwareengineeringbackend.domain.response.Response;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class UserExceptionHandler {
    @ExceptionHandler(value = UserNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public Response userNotFoundException(UserNotFoundException e) {
        return new UserResponseBuilder().withMessage(e.getMessage()).withNotFound().build();
    }

    @ExceptionHandler(value = UserAlreadyExistsException.class)
    @ResponseStatus(value = HttpStatus.CONFLICT)
    public Response userConflictException(UserAlreadyExistsException e) {
        return new UserResponseBuilder().withMessage(e.getMessage()).withConflict().build();
    }
}
