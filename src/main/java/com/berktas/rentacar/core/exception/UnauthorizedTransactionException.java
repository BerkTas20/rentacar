package com.berktas.rentacar.core.exception;

import com.berktas.rentacar.core.exception.base.BaseException;
import lombok.*;
import org.springframework.http.HttpStatus;

@Getter
@Setter

public class UnauthorizedTransactionException extends BaseException {

    public UnauthorizedTransactionException(String message, HttpStatus unauthorized) {
        super(message);
    }


}
