package com.berktas.rentacar.core.validator;


import com.berktas.rentacar.core.exception.validator.ValidatorException;

public interface Validator<T> {
    void validate(T t) throws ValidatorException;
}
