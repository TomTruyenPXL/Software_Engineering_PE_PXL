package com.example.softwareengineeringbackend.rest.handler;

import com.example.softwareengineeringbackend.domain.response.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CsvExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public ResponseEntity<Response> handleMaxSizeException(MaxUploadSizeExceededException e) {
        Response response = new Response().setStatus(400).setMessage("File size is too big");

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }
}
