package com.technocorp.testscrud.exception;

import com.technocorp.testscrud.exception.custom.HttpException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.ResponseStatusException;

@RestControllerAdvice
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<HttpException> handleStatusException(ResponseStatusException ex, WebRequest request) {
        return HttpException.builder()
                .exception(ex)
                .entity();

    }

}
