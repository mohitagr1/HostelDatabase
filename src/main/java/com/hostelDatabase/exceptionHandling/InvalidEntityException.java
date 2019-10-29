package com.hostelDatabase.exceptionHandling;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidEntityException extends Throwable {
    public InvalidEntityException(String exception) {
        super(exception);
    }
}
