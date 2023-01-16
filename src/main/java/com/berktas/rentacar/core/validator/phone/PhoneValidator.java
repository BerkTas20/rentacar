package com.berktas.rentacar.core.validator.phone;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PhoneValidator implements
        ConstraintValidator<Phone, String> {

    @Override
    public void initialize(Phone contactNumber) {
    }

    @Override
    public boolean isValid(String field,
                           ConstraintValidatorContext cxt) {
        if(field != null){
            return field.matches("^+[0-9]+");
        }
        return true;
    }

}