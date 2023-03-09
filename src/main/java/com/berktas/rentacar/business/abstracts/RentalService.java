package com.berktas.rentacar.business.abstracts;

import com.berktas.rentacar.controller.rental.SaveRentalRequest;
import com.berktas.rentacar.model.dto.RentalDetailDto;

import java.time.LocalDateTime;


public interface RentalService {
    RentalDetailDto rent(SaveRentalRequest saveRentalRequest, Long carId);

    RentalDetailDto deliver(Long id, LocalDateTime localDate);

}
