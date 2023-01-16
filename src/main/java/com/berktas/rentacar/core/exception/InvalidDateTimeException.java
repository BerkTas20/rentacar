package com.berktas.rentacar.core.exception;

import com.berktas.rentacar.core.exception.validator.ValidatorException;
import org.springframework.http.HttpStatus;

public class InvalidDateTimeException extends ValidatorException {

    public InvalidDateTimeException(HttpStatus httpStatus, String message) {
        super(httpStatus, message);
    }

    public InvalidDateTimeException(String message) {
        super(message);
    }
}
