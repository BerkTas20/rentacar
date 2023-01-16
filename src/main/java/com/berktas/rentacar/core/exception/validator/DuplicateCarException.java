package com.berktas.rentacar.core.exception.validator;

import com.berktas.rentacar.core.exception.base.BaseException;
import lombok.Getter;

@Getter
public class DuplicateCarException extends BaseException {
    public DuplicateCarException(String message) {
        super(message);
    }
}
