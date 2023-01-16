package com.berktas.rentacar.core.validator;


import com.berktas.rentacar.core.exception.validator.EmptySetException;
import com.berktas.rentacar.core.exception.validator.ValidatorException;
import com.berktas.rentacar.model.dto.EmptySetValidatorDto;
import org.springframework.stereotype.Component;

@Component
public class EmptySetValidator implements Validator<EmptySetValidatorDto> {

    @Override
    public void validate(EmptySetValidatorDto emptySetValidatorDto) throws ValidatorException {

        if (emptySetValidatorDto.getVehicleSet().isEmpty()) {
            throw new EmptySetException(emptySetValidatorDto.getErrorMessage());
        }
    }

}
