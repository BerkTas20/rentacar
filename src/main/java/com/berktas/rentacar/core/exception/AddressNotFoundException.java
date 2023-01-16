package com.berktas.rentacar.core.exception;

import com.berktas.rentacar.core.exception.base.BaseException;
import lombok.AllArgsConstructor;
import lombok.Data;


public class AddressNotFoundException extends BaseException {

    public AddressNotFoundException(String message) {
        super(message);
    }

}
