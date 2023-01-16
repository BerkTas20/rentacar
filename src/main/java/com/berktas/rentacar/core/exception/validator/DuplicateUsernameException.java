package com.berktas.rentacar.core.exception.validator;


import com.berktas.rentacar.core.exception.base.BaseException;
import lombok.Getter;

@Getter
public class DuplicateUsernameException extends BaseException {
    public DuplicateUsernameException(String message) {
        super(message);
    }
}
