package com.berktas.rentacar.model.dto;


import com.berktas.rentacar.core.util.tracing.MetreToKm;
import com.berktas.rentacar.model.dto.base.BaseDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OnlyCarInformationDto extends BaseDto {

    private String licensePlate;

    private String modelYear;

    private long totalDistance = 0L;

    public OnlyCarInformationDto(BaseDtoBuilder<?, ?> b) {
        super(b);
    }

    @JsonProperty(value = "totalDistanceKm")
    private double getTotalDistanceKm() {
        return MetreToKm.convert(totalDistance, 3);
    }


}
