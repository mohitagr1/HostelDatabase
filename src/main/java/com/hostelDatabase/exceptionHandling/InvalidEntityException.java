package com.hostelDatabase.exceptionHandling;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

@ResponseStatus(HttpStatus.BAD_REQUEST)
@Data
@EqualsAndHashCode(callSuper = false)
public class InvalidEntityException extends RuntimeException {
    String exception;
    List<SubError> subErrors;

    public InvalidEntityException(String exception, List<SubError> subErrorList) {
        this.exception = exception;
        this.subErrors = subErrorList;
    }
}
