package com.berktas.rentacar.core.exception;

import com.berktas.rentacar.core.exception.base.BaseException;
import org.springframework.http.HttpStatus;

public class UnauthorizedCarOperationException extends BaseException {

    public UnauthorizedCarOperationException(HttpStatus httpStatus, String message) {
        super(message, httpStatus);
    }

    public UnauthorizedCarOperationException(String message) {
        super(message);
    }
}
