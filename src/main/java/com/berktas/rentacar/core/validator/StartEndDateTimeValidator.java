package com.berktas.rentacar.core.validator;


import com.berktas.rentacar.core.exception.InvalidDateTimeException;
import com.berktas.rentacar.core.exception.validator.ValidatorException;
import com.berktas.rentacar.model.dto.StartEndDateTimeDto;
import org.springframework.stereotype.Component;

@Component
public class StartEndDateTimeValidator implements Validator<StartEndDateTimeDto> {

    @Override
    public void validate(StartEndDateTimeDto startEndDateTimeDto) throws ValidatorException {

        if (startEndDateTimeDto.getEndDateTime().isBefore(startEndDateTimeDto.getStartDateTime())) {

            throw new InvalidDateTimeException("Zaman Parametresinin Bitiş Tarihi Başlangıç Tarihinden Önce Olamaz.");
        }
    }

}
