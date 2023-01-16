package com.berktas.rentacar.core.validator;

import com.berktas.rentacar.core.exception.InvalidDateTimeException;
import com.berktas.rentacar.core.exception.validator.ValidatorException;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class FutureDateTimeValidator implements Validator<LocalDateTime> {

    @Override
    public void validate(LocalDateTime localDateTime) throws ValidatorException {

        if (localDateTime.isAfter(LocalDateTime.now())) {
            throw new InvalidDateTimeException("İleriki bir tarih seçilemez.");
        }
    }

}
