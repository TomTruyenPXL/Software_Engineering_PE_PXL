package com.example.softwareengineeringbackend.domain.builder;

import com.example.softwareengineeringbackend.domain.response.Response;
import org.springframework.http.HttpStatus;

public class UserResponseBuilder {
    private final Response response;

    public UserResponseBuilder() {
        this.response = new Response();
    }

    public UserResponseBuilder withPath(String path) {
        this.response.setPath(path);

        return this;
    }

    public UserResponseBuilder withMessage(String message) {
        this.response.setMessage(message);

        return this;
    }

    public UserResponseBuilder withToken(String token) {
        this.response.setToken(token);

        return this;
    }

    public UserResponseBuilder withBadRequest() {
        this.response.setStatus(HttpStatus.BAD_REQUEST.value());

        return this;
    }

    public UserResponseBuilder withNotFound() {
        this.response.setStatus(HttpStatus.NOT_FOUND.value());

        return this;
    }

    public UserResponseBuilder withConflict() {
        this.response.setStatus(HttpStatus.CONFLICT.value());

        return this;
    }

    public UserResponseBuilder withInternalError() {
        this.response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());

        return this;
    }

    public UserResponseBuilder withOk() {
        this.response.setStatus(HttpStatus.OK.value());
        this.response.setSuccess(true);

        return this;
    }

    public Response build() {
        return response;
    }
}
