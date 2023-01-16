package com.berktas.rentacar.core.exception.base;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class BaseException extends RuntimeException {
    @Getter
    private final HttpStatus httpStatus;
    @Getter
    private String field;

    public BaseException(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }

    public BaseException(String field,String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
        this.field=field;
    }

    public BaseException(String field,String message) {
        super(message);
        this.httpStatus = (HttpStatus.BAD_REQUEST);
        this.field=field;
    }

    public BaseException() {
        super();
        httpStatus = (HttpStatus.BAD_REQUEST);
    }

    public BaseException(String message) {
        super(message);
        httpStatus = (HttpStatus.BAD_REQUEST);
    }

}

