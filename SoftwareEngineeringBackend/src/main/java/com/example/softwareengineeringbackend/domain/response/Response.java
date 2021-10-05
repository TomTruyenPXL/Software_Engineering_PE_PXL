package com.example.softwareengineeringbackend.domain.response;

import java.time.LocalDateTime;

public class Response {
    private String path;
    private String message;
    private String token;
    private boolean success;
    private int status;
    private LocalDateTime time;

    public Response() {
        this.message = "";
        this.success = true;
        this.time = LocalDateTime.now();
    }

    public String getPath() {
        return path;
    }

    public Response setPath(String path) {
        this.path = path;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public Response setMessage(String message) {
        this.message = message;
        return this;
    }

    public String getToken() {
        return token;
    }

    public Response setToken(String token) {
        this.token = token;
        return this;
    }

    public boolean isSuccess() {
        return success;
    }

    public Response setSuccess(boolean success) {
        this.success = success;
        return this;
    }

    public int getStatus() {
        return status;
    }

    public Response setStatus(int status) {
        this.status = status;
        return this;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public Response setTime(LocalDateTime time) {
        this.time = time;
        return this;
    }

}
