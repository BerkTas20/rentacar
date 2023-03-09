package com.berktas.rentacar.core.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class UnauthorizedTransactionException extends RuntimeException {
    public UnauthorizedTransactionException(String message, HttpStatus unauthorized) {
        super(message);
    }
}
