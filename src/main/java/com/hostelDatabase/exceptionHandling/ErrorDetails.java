package com.hostelDatabase.exceptionHandling;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
public class ErrorDetails extends SubError {
    private String message;
    private String object;
    private String field;
    private Object rejectedValue;

    ErrorDetails(String object, String message) {
        this.object = object;
        this.message = message;
    }

    public ErrorDetails(String object, String field, String rejectedValue, String message) {
        super();
    }
}
