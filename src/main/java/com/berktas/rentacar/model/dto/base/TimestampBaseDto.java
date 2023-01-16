package com.berktas.rentacar.model.dto.base;

import lombok.Data;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@SuperBuilder
@Data
public abstract class TimestampBaseDto extends BaseDto {
    private LocalDateTime createdDateTime;
    private LocalDateTime updatedDateTime;
}
