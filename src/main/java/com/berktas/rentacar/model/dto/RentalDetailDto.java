package com.berktas.rentacar.model.dto;

import com.berktas.rentacar.model.dto.base.TimestampBaseDto;
import lombok.Data;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;


@SuperBuilder
@ToString
@Data
public class RentalDetailDto extends TimestampBaseDto {
    public LocalDateTime returnDate;
    public LocalDateTime rentDate;
    private Integer day;
    private Double price;
    private String plate;
}
