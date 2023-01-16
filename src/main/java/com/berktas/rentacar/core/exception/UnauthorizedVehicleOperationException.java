package com.berktas.rentacar.core.exception;

import com.berktas.rentacar.core.exception.base.BaseException;
import org.springframework.http.HttpStatus;

public class UnauthorizedVehicleOperationException extends BaseException {

    public UnauthorizedVehicleOperationException(HttpStatus httpStatus, String message) {
        super(message, httpStatus);
    }

    public UnauthorizedVehicleOperationException(String message) {
        super(message);
    }
}
