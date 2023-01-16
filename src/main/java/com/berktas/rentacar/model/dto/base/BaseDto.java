package com.berktas.rentacar.model.dto.base;


import lombok.Data;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@ToString
@SuperBuilder
@Data
public abstract class BaseDto {
    private Long id;
}
