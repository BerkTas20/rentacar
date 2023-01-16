package com.berktas.rentacar.controller.rental;

import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

import java.time.LocalDateTime;

@Data
@Builder
@Jacksonized
public class SaveRentalRequest {
    public LocalDateTime rentDate;
    public LocalDateTime returnDate;

//    private Long carId;
}
