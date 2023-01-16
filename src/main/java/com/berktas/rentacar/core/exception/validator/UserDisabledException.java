package com.berktas.rentacar.core.exception.validator;

import com.berktas.rentacar.core.exception.base.BaseException;

public class UserDisabledException extends BaseException {
    public UserDisabledException(String message) {
        super(message);
    }
}
