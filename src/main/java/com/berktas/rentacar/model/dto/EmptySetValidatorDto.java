package com.berktas.rentacar.model.dto;

import com.berktas.rentacar.model.entity.Car;
import lombok.Getter;

import java.util.Set;

@Getter
public class EmptySetValidatorDto {

    private final Set<Car> vehicleSet;
    private final String errorMessage;

    private EmptySetValidatorDto(Set<Car> vehicleSet, String errorMessage) {
        this.vehicleSet = vehicleSet;
        this.errorMessage = errorMessage;
    }

    public static EmptySetValidatorDto create(Set<Car> vehicleSet, String errorMessage) {
        return new EmptySetValidatorDto(vehicleSet, errorMessage);
    }
}
