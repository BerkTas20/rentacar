package com.berktas.rentacar.model.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class StartEndDateTimeDto {

    private final LocalDateTime startDateTime;
    private final LocalDateTime endDateTime;

    private StartEndDateTimeDto(LocalDateTime startDateTime, LocalDateTime endDateTime) {
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
    }

    public static StartEndDateTimeDto create(LocalDateTime startDateTime, LocalDateTime endDateTime) {
        return new StartEndDateTimeDto(startDateTime, endDateTime);
    }

}
