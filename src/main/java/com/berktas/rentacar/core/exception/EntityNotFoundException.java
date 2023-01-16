package com.berktas.rentacar.core.exception;

import com.berktas.rentacar.core.exception.base.BaseException;
import org.springframework.http.HttpStatus;

public class EntityNotFoundException extends BaseException {
    public EntityNotFoundException() {
        super("Bulunamadı", HttpStatus.NOT_FOUND);
    }

    public EntityNotFoundException(String message) {
        super(message, HttpStatus.NOT_FOUND);
    }
}
