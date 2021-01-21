package com.technocorp.testscrud.exception;

import com.technocorp.testscrud.exception.custom.HttpException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ResponseStatusException;

import java.util.Objects;

public class RestExceptionBuilder {

    private int httpStatus;
    private String error;
    private String message;

    public RestExceptionBuilder httpStatus(int status) {
        this.httpStatus = status;
        return this;
    }

    public RestExceptionBuilder httpStatus(HttpStatus status) {
        this.httpStatus = status.value();
        if (status.isError()) {
            this.error = status.getReasonPhrase();
        }
        return this;
    }

    public RestExceptionBuilder error(String error) {
        this.error = error;
        return this;
    }

    public RestExceptionBuilder message(String message) {
        this.message = message;
        return this;
    }

    public RestExceptionBuilder exception(ResponseStatusException exception) {
        HttpStatus status = exception.getStatus();
        this.httpStatus = status.value();

        if (!Objects.requireNonNull(exception.getReason()).isBlank()) {
            this.message = exception.getReason();
        }

        if (status.isError()) {
            this.error = status.getReasonPhrase();
        }

        return this;
    }

    public HttpException build() {
        return new HttpException(httpStatus,error,message);
    }

    public ResponseEntity<HttpException> entity() {
        return ResponseEntity.status(httpStatus).headers(HttpHeaders.EMPTY).body(build());
    }


}
