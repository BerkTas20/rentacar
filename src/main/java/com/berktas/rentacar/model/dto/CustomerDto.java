package com.berktas.rentacar.model.dto;

import com.berktas.rentacar.model.dto.base.TimestampBaseDto;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import lombok.extern.jackson.Jacksonized;

@Getter
@SuperBuilder
@ToString
@Jacksonized
public class CustomerDto extends TimestampBaseDto {

    private String title;
    public String companyName;
    private String firstName;
    private String lastName;
}
