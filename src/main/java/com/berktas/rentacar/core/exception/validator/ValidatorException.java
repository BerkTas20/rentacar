package com.berktas.rentacar.core.exception.validator;

import com.berktas.rentacar.core.exception.base.BaseException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@AllArgsConstructor
public class ValidatorException extends BaseException {
    public ValidatorException(HttpStatus httpStatus, String message) {
        super(message, httpStatus);
    }
    public ValidatorException(String message) {
        super(message);
    }
}
