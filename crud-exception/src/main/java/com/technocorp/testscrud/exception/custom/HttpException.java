package com.technocorp.testscrud.exception.custom;

import com.technocorp.testscrud.exception.RestExceptionBuilder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper=false)
public class HttpException extends RuntimeException {


    private final LocalDateTime timestamp = LocalDateTime.now();
    private final int httpStatus;
    private final String error;
    private final String message;

    public static RestExceptionBuilder builder(){
        return new RestExceptionBuilder();
    }
}
