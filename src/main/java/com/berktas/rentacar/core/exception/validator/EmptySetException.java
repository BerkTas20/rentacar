package com.berktas.rentacar.core.exception.validator;

import org.springframework.http.HttpStatus;

public class EmptySetException extends ValidatorException {

    public EmptySetException(HttpStatus httpStatus, String message) {
        super(httpStatus, message);
    }

    public EmptySetException(String message) {
        super(message);
    }
}
