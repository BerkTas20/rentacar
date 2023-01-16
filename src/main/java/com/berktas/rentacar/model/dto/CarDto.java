package com.berktas.rentacar.model.dto;

import com.berktas.rentacar.model.dto.base.TimestampBaseDto;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import lombok.extern.jackson.Jacksonized;

import java.time.LocalDateTime;

@Getter
@SuperBuilder
@ToString
@Jacksonized
public class CarDto extends TimestampBaseDto {
    public String carName;
    public int modelYear;
    public double dailyPrice;
    public String description;
    public String brandName;
    public String colorName;

}
