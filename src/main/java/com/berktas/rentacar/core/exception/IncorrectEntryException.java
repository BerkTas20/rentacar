package com.berktas.rentacar.core.exception;

import com.berktas.rentacar.core.exception.base.BaseException;
import lombok.Getter;

@Getter
public class IncorrectEntryException extends BaseException {
    public IncorrectEntryException(String message) {
        super(message);
    }

}
